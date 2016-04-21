# Localization-and-Corner-Detection-with-R12-Robotic-Arm

R12 robotic arm has shoulder, elbow, waist and twist joints to move the end effector. A camera is attached to the end effector. The purpose is to find the corners of a box that could be present anywhere in its workspace.

Algorithm Methodology
-Robot will take a series of pictures by dividing the workspace into sectors
-Use Computer vision to know about the status of the object in the sector
-Moves to the most appropriate quadrilateral in the sector where the possibility of box is high
-Subsequently takes pictures and moves to a corner depending on custom developed Fuzzy logic

Robot Controller
-Built a Custom GUI to control the Robotic arm remotely and for calibration
-Robot controlled via HTTP protocol

Presented the GUI to future students for using the Robot easily

Image Processing
-The image is blurred, applied threshold based on Hue and found the centroid based on the moments
-This is used to detect the quadrilateral of the image in the sector workspace
-Later, I found the possible contours, applied convex hull to the max size contour and applied an approximate polygon on top of it

Quantization of the image
-The image is quantized into to 3X3 grid based on the presence of the box, where each cell represents the ratio of the box that fills the space
-It can detect edge, corners using that
-Depending on the observation a weighted vector score is computed to make the Robot move for the next iteration.

Tools and Libraries
Java, JavaFX, OpenCV, Eclipse
