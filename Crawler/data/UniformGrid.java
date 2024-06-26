2
https://raw.githubusercontent.com/aistairc/GeoFlink/master/src/main/java/GeoFlink/spatialIndices/UniformGrid.java
/*
Copyright 2020 Data Platform Research Team, AIRC, AIST, Japan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package GeoFlink.spatialIndices;

import GeoFlink.spatialObjects.Point;
import GeoFlink.utils.HelperClass;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.api.java.tuple.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniformGrid implements Serializable {

    double minX;     //X - East-West longitude
    double maxX;
    double minY;     //Y - North-South latitude
    double maxY;

    final private int CELLINDEXSTRLENGTH = 5;
    double cellLength;
    int numGridPartitions;
    double cellLengthMeters;
    HashSet<String> girdCellsSet = new HashSet<String>();

    //TODO: Remove variable cellLengthMeters (Deprecated)

    public UniformGrid(int uniformGridRows, double minX, double maxX, double minY, double maxY)
    {
        this.minX = minX;     //X - East-West longitude
        this.maxX = maxX;
        this.minY = minY;     //Y - North-South latitude
        this.maxY = maxY;

        double xAxisDiff = maxX - minX;
        double yAxisDiff = maxY - minY;

        this.numGridPartitions = uniformGridRows;

        // Adjusting coordinates to make square grid cells
        if(xAxisDiff > yAxisDiff)
        {
            double diff = xAxisDiff - yAxisDiff;
            maxY += diff / 2;
            minY -= diff / 2;
        }
        else if(yAxisDiff > xAxisDiff)
        {
            double diff = yAxisDiff - xAxisDiff;
            maxX += diff / 2;
            minX -= diff / 2;
        }

        this.cellLength = (maxX - minX) / uniformGridRows;
        System.out.println("cellLength: " + cellLength);
        this.cellLengthMeters = HelperClass.computeHaverSine(minX, minY, minX + cellLength, minY);

        // Populating the girdCellset - contains all the cells in the grid
        for (int i = 0; i < uniformGridRows; i++) {
            for (int j = 0; j < uniformGridRows; j++) {
                String cellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                girdCellsSet.add(cellKey);
            }
        }
    }

    public double getMinX() {return minX;}
    public double getMinY() {return minY;}
    public double getMaxX() {return maxX;}
    public double getMaxY() {return maxY;}
    public int getCellIndexStrLength() {return CELLINDEXSTRLENGTH;}

    public int getNumGridPartitions()
    {
        return numGridPartitions;
    }
    public double getCellLength() {return cellLength;}
    public double getCellLengthInMeters() {return cellLengthMeters;}
    public HashSet<String> getGirdCellsSet() {return girdCellsSet;}

    /*
    getGuaranteedNeighboringCells: returns the cells containing the guaranteed r-neighbors
    getCandidateNeighboringCells: returns the cells containing the candidate r-neighbors and require distance computation
    The output set of the above two functions are mutually exclusive
    */
    public HashSet<String> getGuaranteedNeighboringCells(double queryRadius, Point queryPoint)
    {
        //queryRadius = CoordinatesConversion.metersToDD(queryRadius,cellLength,cellLengthMeters); //UNCOMMENT FOR HAVERSINE (METERS)
        System.out.println("queryRadius in Lat/Lon: "+ queryRadius);
        String queryCellID = queryPoint.gridID;

        HashSet<String> guaranteedNeighboringCellsSet = new HashSet<String>();
        int guaranteedNeighboringLayers = getGuaranteedNeighboringLayers(queryRadius);
        if(guaranteedNeighboringLayers == 0)
        {
            guaranteedNeighboringCellsSet.add(queryCellID);
        }
        else if(guaranteedNeighboringLayers > 0)
        {
            ArrayList<Integer> queryCellIndices = HelperClass.getIntCellIndices(queryCellID);       //converts cellID String->Integer

            for(int i = queryCellIndices.get(0) - guaranteedNeighboringLayers; i <= queryCellIndices.get(0) + guaranteedNeighboringLayers; i++)
                for(int j = queryCellIndices.get(1) - guaranteedNeighboringLayers; j <= queryCellIndices.get(1) + guaranteedNeighboringLayers; j++)
                {
                    if(validKey(i,j))
                    {
                        String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                        guaranteedNeighboringCellsSet.add(neighboringCellKey);
                    }
                }
        }

        return guaranteedNeighboringCellsSet;
    }

    public boolean validKey(int x, int y){
        if(x < numGridPartitions && y < numGridPartitions)
        {return true;}
        else
        {return false;}
    }

    // Return all the neighboring cells up to the given grid layer
    public HashSet<String> getNeighboringCellsByLayer(Point p, int numNeighboringLayers)
    {
        //queryRadius = CoordinatesConversion.metersToDD(queryRadius,cellLength,cellLengthMeters); // UNCOMMENT FOR HAVERSINE (METERS)
        String givenCellID = p.gridID;
        HashSet<String> neighboringCellsSet = new HashSet<String>();

        if(numNeighboringLayers <= 0)
        {
            System.out.println("candidateNeighboringLayers cannot be 0 or less");
            System.exit(1); // Unsuccessful termination
        }
        else //numNeighboringLayers > 0
        {
            ArrayList<Integer> cellIndices = HelperClass.getIntCellIndices(givenCellID);

            for(int i = cellIndices.get(0) - numNeighboringLayers; i <= cellIndices.get(0) + numNeighboringLayers; i++)
                for(int j = cellIndices.get(1) - numNeighboringLayers; j <= cellIndices.get(1) + numNeighboringLayers; j++)
                {
                    if(validKey(i,j))
                    {
                        String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                        neighboringCellsSet.add(neighboringCellKey);
                    }
                }
        }
        return neighboringCellsSet;

    }

    // Return all the neighboring cells including candidate cells and guaranteed cells
    public HashSet<String> getNeighboringCells(double queryRadius, Point queryPoint)
    {
        // return all the cells in the set
        if(queryRadius == 0){
            return this.girdCellsSet;
        }

        //queryRadius = CoordinatesConversion.metersToDD(queryRadius,cellLength,cellLengthMeters); // UNCOMMENT FOR HAVERSINE (METERS)
        String queryCellID = queryPoint.gridID;
        HashSet<String> neighboringCellsSet = new HashSet<String>();
        int numNeighboringLayers = getCandidateNeighboringLayers(queryRadius);

        if(numNeighboringLayers <= 0)
        {
            System.out.println("candidateNeighboringLayers cannot be 0 or less");
            System.exit(1); // Unsuccessful termination
        }
        else //numNeighboringLayers > 0
        {
            ArrayList<Integer> queryCellIndices = HelperClass.getIntCellIndices(queryCellID);

            for(int i = queryCellIndices.get(0) - numNeighboringLayers; i <= queryCellIndices.get(0) + numNeighboringLayers; i++)
                for(int j = queryCellIndices.get(1) - numNeighboringLayers; j <= queryCellIndices.get(1) + numNeighboringLayers; j++)
                {
                    if(validKey(i,j))
                    {
                        String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                        neighboringCellsSet.add(neighboringCellKey);
                    }
                }

        }
        return neighboringCellsSet;
    }

    public HashSet<String> getCandidateNeighboringCells(double queryRadius, Point queryPoint, Set<String> guaranteedNeighboringCellsSet)
    {
        // queryRadius = CoordinatesConversion.metersToDD(queryRadius,cellLength,cellLengthMeters);  //UNCOMMENT FOR HAVERSINE (METERS)
        String queryCellID = queryPoint.gridID;
        HashSet<String> candidateNeighboringCellsSet = new HashSet<String>();
        int candidateNeighboringLayers = getCandidateNeighboringLayers(queryRadius);

        if(candidateNeighboringLayers <= 0)
        {
            System.out.println("candidateNeighboringLayers cannot be 0 or less");
            System.exit(1); // Unsuccessful termination
        }
        else //candidateNeighboringLayers > 0
        {
            ArrayList<Integer> queryCellIndices = HelperClass.getIntCellIndices(queryCellID);
            int count = 0;

            for(int i = queryCellIndices.get(0) - candidateNeighboringLayers; i <= queryCellIndices.get(0) + candidateNeighboringLayers; i++)
                for(int j = queryCellIndices.get(1) - candidateNeighboringLayers; j <= queryCellIndices.get(1) + candidateNeighboringLayers; j++)
                {
                    if(validKey(i,j)) {
                        String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                        if (!guaranteedNeighboringCellsSet.contains(neighboringCellKey)) // Add key if and only if it exist in the gridCell and is not included in the guaranteed neighbors
                        {
                            count++;
                            candidateNeighboringCellsSet.add(neighboringCellKey);
                        }
                    }
                }
            System.out.println("Candidate neighbouring cells: " + count);
        }

        return candidateNeighboringCellsSet;
    }

    private int getGuaranteedNeighboringLayers(double queryRadius)
    {

        double cellDiagonal = cellLength*Math.sqrt(2);

        int numberOfLayers = (int)Math.floor((queryRadius/cellDiagonal) - 1); // Subtract 1 because we do not consider the cell with the query object as a layer i
        System.out.println("Guaranteed Number of Layers: "+ numberOfLayers );
        return numberOfLayers;
        // If return value = -1 then not even the cell containing the query is guaranteed to contain r-neighbors
        // If return value = 0 then only the cell containing the query is guaranteed to contain r-neighbors
        // If return value is a positive integer n, then the n layers around the cell containing the query is guaranteed to contain r-neighbors
    }

    public int getCandidateNeighboringLayers(double queryRadius)
    {
        int numberOfLayers = (int)Math.ceil(queryRadius/cellLength);
        return numberOfLayers;
    }

    public HashSet<String> getNeighboringLayerCells(Point queryPoint, int layerNumber)
    {
        String queryCellID = queryPoint.gridID;
        HashSet<String> neighboringLayerCellsSet = new HashSet<String>();
        HashSet<String> neighboringLayerCellsToExcludeSet = new HashSet<String>();
        ArrayList<Integer> queryCellIndices = HelperClass.getIntCellIndices(queryCellID);       //converts cellID String->Integer

        //Get the cells to exclude, iff layerNumber is greater than 0
        if(layerNumber > 0)
        {
            for(int i = queryCellIndices.get(0) - layerNumber + 1; i <= queryCellIndices.get(0) + layerNumber - 1; i++)
                for(int j = queryCellIndices.get(1) - layerNumber + 1; j <= queryCellIndices.get(1) + layerNumber -1; j++)
                {
                    if(validKey(i,j)) {
                        String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                        neighboringLayerCellsToExcludeSet.add(neighboringCellKey);
                    }
                }
        }

        for(int i = queryCellIndices.get(0) - layerNumber; i <= queryCellIndices.get(0) + layerNumber; i++)
            for(int j = queryCellIndices.get(1) - layerNumber; j <= queryCellIndices.get(1) + layerNumber; j++)
            {
                if(validKey(i,j))
                {
                    String neighboringCellKey = HelperClass.padLeadingZeroesToInt(i, CELLINDEXSTRLENGTH) + HelperClass.padLeadingZeroesToInt(j, CELLINDEXSTRLENGTH);
                    if (!neighboringLayerCellsToExcludeSet.contains(neighboringCellKey)) // Add key if and only if it exist in the gridCell
                    {
                        neighboringLayerCellsSet.add(neighboringCellKey);
                    }
                }
            }
        return neighboringLayerCellsSet;
    }

    // Returns all the neighboring layers of point p, where each layer consists of a number of cells
    public ArrayList<HashSet<String>> getAllNeighboringLayers(Point p)
    {
        ArrayList<HashSet<String>> listOfSets = new ArrayList<HashSet<String>>();

        for(int i = 0; i < numGridPartitions; i++)
        {
            HashSet<String> neighboringLayerCellSet = getNeighboringLayerCells(p, i);

            if(neighboringLayerCellSet.size() > 0)
            {
                listOfSets.add(neighboringLayerCellSet);
            }
            else
            {
                break; // break the for loop as soon as we find an empty neighboringLayerCellSet
            }
        }
        return listOfSets;
    }


    public static class getCellsFilteredByLayer extends RichFilterFunction<Tuple2<String, Integer>>
    {
        private final HashSet<String> CellIDs; // CellIDs are input parameters

        //ctor
        public getCellsFilteredByLayer(HashSet<String> CellIDs)
        {
            this.CellIDs = CellIDs;
        }

        @Override
        public boolean filter(Tuple2<String, Integer> cellIDCount) throws Exception
        {
            return CellIDs.contains(cellIDCount.f0);
        }
    }
}
