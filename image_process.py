from skimage import io, filters, transform, util
import matplotlib.pyplot as plt
#import cv2
import numpy as np
from scipy import misc
import zip
import os
import argparse

TRAINING_SIZE = 6


def change_label(path_source):
    for root, dirs, files in os.walk(path_source):
        for index, file_name in enumerate(files):
            abs_name = os.path.join(root, file_name)
            new_name = os.path.join(root, str(index) + ".png")
            os.rename(abs_name, new_name)
    os.close(0)


def image_curate(path_source, dest_path):
    for i in range(TRAINING_SIZE):
        im_source = os.path.join(path_source, "{}.png".format(i))
        im_dest = os.path.join(dest_path, "{}.png".format(i))
        im_read = io.imread(im_source)
        im_invert = util.invert(im_read)
        # im_filter = filters.inverse(im_invert, 2)
        im_filter = filters.gaussian(im_invert, 5, multichannel=False)
        im_transform = transform.resize(im_filter, (120, 120))
        misc.imsave(im_dest, im_transform)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Add some argument there")
    parser.add_argument("path_source", help="Put source image path there")
    parser.add_argument("dest_path", metavar="FILE", help="Put destination path there")
    args = parser.parse_args()
    #change_label(args.path_source)   # Buggy function    ## For changing image label
    image_curate(args.path_source, args.dest_path)
    io.find_available_plugins()
