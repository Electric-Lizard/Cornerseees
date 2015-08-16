package eii.cornerseees.server.core.cornerseees;

import eei.cornerseees.client.gamefield.model.PieceModel;
import eei.cornerseees.shared.gamefield.GameField;
import eei.cornerseees.shared.gamefield.Piece;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesGameField implements GameField {
    protected CornerseeesCell[][] cells;
    protected int size;

    public CornerseeesGameField(int size) {
        this.size = size;
        cells = new CornerseeesCell[size][size];
        reset();
    }

    @Override
    public CornerseeesCell[][] getCells() {
        return cells;
    }

    public void movePiece(PieceMoving pieceMoving) throws CellNotFoundException, InvalidPieceMovingException,
            InvalidPieceTakingException {
        CornerseeesCell givingCell = null;
        CornerseeesCell acceptingCell = null;

        //check availability
        if (!isMoveAvailable(pieceMoving)) throw new InvalidPieceMovingException();

        //find cells
        for (int row = 0; row < cells.length; row++) {
            CornerseeesCell[] cellRow = cells[row];
            for (int column = 0; column < cellRow.length; column++) {
                int[] givingCellCoordinates = pieceMoving.getGivingCellCoordinates();
                int[] acceptingCellCoordinates = pieceMoving.getAcceptingCellCoordinates();
                int[] iteratingCellCoordinates = new int[] {row, column};
                if (Arrays.equals(iteratingCellCoordinates, givingCellCoordinates)) {
                    givingCell = cells[row][column];
                }
                if (Arrays.equals(iteratingCellCoordinates, acceptingCellCoordinates)) {
                    acceptingCell = cells[row][column];
                }
            }
        }
        //verify cells
        if (givingCell == null || acceptingCell == null) {
            throw new CellNotFoundException();
        }
        if (givingCell.getPiece() == null || acceptingCell.getPiece() != null) {
            throw new InvalidPieceMovingException();
        }
        //move cells
        CornerseeesPiece movingPiece = givingCell.getPiece();
        givingCell.setPiece(null);
        acceptingCell.setPiece(movingPiece);
    }

    public boolean isMoveAvailable(PieceMoving pieceMoving) throws InvalidPieceTakingException {
        return collectionContains(
                getAvailableSteps(new PieceTaking(pieceMoving.getGivingCellCoordinates())),
                pieceMoving.getAcceptingCellCoordinates()
        );
    }

    public Set<int[]> getAvailableSteps(PieceTaking pieceTaking) throws InvalidPieceTakingException {
        int[] cellCoordinate = pieceTaking.getCellCoordinates();
        CornerseeesCell cell = cells[cellCoordinate[0]][cellCoordinate[1]];
        CornerseeesPiece takenPiece = cell.getPiece();
        if (takenPiece == null) {
            throw new InvalidPieceTakingException();
        }
        Piece.Team takenPieceTeam = takenPiece.getTeam();

        Set<int[]> availableCellCoordinates = new HashSet<>();
        for (int[] direction : getNeighbourDirections(1)) {
            int[] nearlyCoordinates = new int[] {cellCoordinate[0]+direction[0], cellCoordinate[1]+direction[1]};
            if (!isCoordinateInBounds(nearlyCoordinates, new int[] {size, size})) continue;
            CornerseeesCell nearlyCell = cells[nearlyCoordinates[0]][nearlyCoordinates[1]];
            CornerseeesPiece nearlyCellPiece = nearlyCell.getPiece();
            if (nearlyCellPiece == null) {
                availableCellCoordinates.add(nearlyCoordinates);
            }
            availableCellCoordinates.addAll(getAvailableJumps(cellCoordinate));
        };

        return availableCellCoordinates;
    }

    public Set<int[]> getAvailableJumps(int[] coordinate) {
        return getAvailableJumps(coordinate, new HashSet<>());
    }
    public Set<int[]> getAvailableJumps(int[] coordinate, Set<int[]> availableJumps) {
        for (int[] direction : getNeighbourDirections(1)) {
            int[] nearlyCoordinates = new int[] {coordinate[0]+direction[0], coordinate[1]+direction[1]};
            if (!isCoordinateInBounds(nearlyCoordinates, new int[] {size, size})) continue;
            CornerseeesCell nearlyCell = cells[nearlyCoordinates[0]][nearlyCoordinates[1]];
            CornerseeesPiece nearlyCellPiece = nearlyCell.getPiece();
            if (nearlyCellPiece != null) {
                int[] cellToJumpCoordinates =
                        new int[] {2*nearlyCoordinates[0]-coordinate[0], 2*nearlyCoordinates[1]-coordinate[1]};
                if (!isCoordinateInBounds(cellToJumpCoordinates, new int[] {size, size})) continue;
                CornerseeesCell cellToJump = cells[cellToJumpCoordinates[0]][cellToJumpCoordinates[1]];
                if (!collectionContains(availableJumps, cellToJumpCoordinates) && cellToJump.getPiece() == null) {
                    availableJumps.add(cellToJumpCoordinates);
                    availableJumps.addAll(getAvailableJumps(cellToJumpCoordinates, availableJumps));
                }
            }
        }
        return availableJumps;
    }

    static public boolean isCoordinateInBounds(int[] coordinate, int[] bounds) {
        return coordinate[0] < bounds[0] && coordinate[1] < bounds[1] && coordinate[0] >= 0 && coordinate[1] >= 0;
    }
    
    static public boolean collectionContains(Collection<int[]> haystack, int[] niddle) {
        boolean isContains = false;
        for (int[] compared : haystack) {
            if (Arrays.equals(compared, niddle)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    static public Set<int[]> getNeighbourDirections(int distance) {
        if (distance < 0) throw new IllegalArgumentException("Distance must be positive integer");

        Set<int[]> neighbourDirections = new HashSet<>();
        for (int i = 0; i < distance; i++) {
            int a = distance - i;
            for (int[] direction : new int[][] {{a, a}, {0, a}, {-a, a}, {-a, 0}, {-a, -a}, {-0, -a}, {a, -a}, {a, 0}}) {
                neighbourDirections.add(direction);
            }
        }
        return neighbourDirections;
    }

    protected void reset() {
        int teamSize = size/2;

        //Team blue
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                CornerseeesCell cell = new CornerseeesCell();
                //Team Blue
                if (row < teamSize && column < teamSize) {
                    cell.setPiece(new CornerseeesPiece(PieceModel.Team.BLUE));
                }
                //Team Red
                if (size - row <= teamSize && size - column <= teamSize) {
                    cell.setPiece(new CornerseeesPiece(PieceModel.Team.RED));
                }
                cells[row][column] = cell;
            }
        }
    }
}
