from sklearn.datasets import load_svmlight_file, dump_svmlight_file
import sys

if __name__ == "__main__":
    file_name = sys.argv[1]
    out_file = file_name+".one"
    x, y =load_svmlight_file(file_name)
    dump_svmlight_file(x, y, out_file, zero_based=False)
    print("done")