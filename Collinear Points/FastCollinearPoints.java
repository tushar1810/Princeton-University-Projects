import java.util.ArrayList;
import java.util.Arrays;

/* *****************************************************************************
 *  Name:Tushar Sethi
 **************************************************************************** */
public class FastCollinearPoints {
    private int num;
    private LineSegment[] arr;

    public FastCollinearPoints(Point[] points) {
        num = 0;
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] pointss = Arrays.copyOf(points, points.length);
        Point[] pointsco = Arrays.copyOf(points, points.length);
        Arrays.sort(pointss);
        for (int i = 1; i < points.length; i++) {
            if (pointss[i] == pointss[i - 1]) {
                throw new IllegalArgumentException();
            }
        }
        ArrayList<LineSegment> ans = new ArrayList<LineSegment>();

        for (int i = 0; i < pointss.length; i++) {
            Arrays.sort(pointsco, pointss[i].slopeOrder());
            // double pre = temp.get(0);
            int p = 1;
            for (int j = 0; j < pointss.length - 1; j++) {
                if (pointss[i].slopeTo(pointsco[j]) == pointss[i].slopeTo(pointsco[j + 1])) {
                    p++;
                    if (p == 2) p++;
                    if (p >= 4 && j == pointss.length - 2) {
                        ans.add(new LineSegment(pointss[i], pointsco[j + 1]));
                    }
                }
                else if (p >= 4) {
                    if (pointss[i].compareTo(pointsco[j]) > 0) {
                        ans.add(new LineSegment(pointss[i], pointsco[j]));
                        p = 1;
                    }
                    else p = 1;
                }
                else {
                    p = 1;
                }
            }
        }
        arr = ans.toArray(new LineSegment[ans.size()]);

    }

    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return arr.length;
    }

    // the number of line segments
    public LineSegment[] segments() {
        return arr;
    }

    // the line segments

    
}
