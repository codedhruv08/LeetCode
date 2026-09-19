class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x and y coordinates on the rectangle to the circle center
        int closestX = clamp(xCenter, x1, x2);
        int closestY = clamp(yCenter, y1, y2);
        
        // Calculate the distance vector components from center to closest point
        int distX = xCenter - closestX;
        int distY = yCenter - closestY;
        
        // Check if the squared distance is <= squared radius
        return (distX * distX) + (distY * distY) <= (radius * radius);
    }
    
    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
