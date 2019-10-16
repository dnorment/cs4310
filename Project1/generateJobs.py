import random

def generateJobs(num, maxTime=15):
    print("Generating job.txt:")

    with open("Project1/job.txt",'w') as f:
        
        for i in range(num):
            f.write("Job{0}\n{1}".format(str(i+1), str(random.randint(1, maxTime))))
            if i+1 is not num:
                f.write("\n")

        f.close()

if __name__ == "__main__":
    generateJobs(10)