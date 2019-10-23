from job import Job

import random

def generateJobs(num, maxTime=20):

    with open("Project1/job.txt",'w') as f:
        
        try:
            for i in range(num):
                f.write("Job{0}\n{1}".format(str(i+1), str(random.randint(1, maxTime))))
                if i+1 is not num:
                    f.write("\n")
        finally:
            f.close()

def readJobs(num):
    try:
        jobList = []
        with open("Project1/job.txt",'r') as f:
            for i in range(num):
                nn = f.readline()
                rt = int(f.readline())
                num = int(nn.strip("Job"))
                jobList.append(Job(num, rt))
    finally:
        f.close()
    
    return jobList