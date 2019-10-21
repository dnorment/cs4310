from job import Job

import numpy as np
import random

def generateJobs(num, maxTime=15):

    print("Generating job.txt:")

    with open("Project1/job.txt",'w') as f:
        
        try:
            for i in range(num):
                f.write("Job{0}\n{1}".format(str(i+1), str(random.randint(1, maxTime))))
                if i+1 is not num:
                    f.write("\n")
        finally:
            f.close()
            print("Generated job.txt")

def readJobs(num):
    try:
        print("Reading jobs into ndarray:")
        jobList = []
        with open("Project1/job.txt",'r') as f:
            for i in range(num):
                nn = f.readline()
                rt = int(f.readline())
                num = int(nn.strip("Job"))
                jobList.append(Job(num, rt))
    finally:
        f.close()
        jobArray = np.array(jobList)
        print("Read jobs into ndarray")
    
    return jobArray