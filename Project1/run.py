from job import Job
from generateJobs import generateJobs
import numpy as np

NUM_JOBS = 5
#generateJobs(NUM_JOBS)

joblist = []

try:
    print("Reading jobs into ndarray:")
    with open("Project1/job.txt",'r') as f:
        for i in range(NUM_JOBS):
            nn = f.readline()
            rt = int(f.readline())
            num = int(nn.strip("Job"))
            joblist.append(Job(num, rt))
finally:
    f.close()
    jobarray = np.array(joblist)
    print("Read jobs into ndarray")