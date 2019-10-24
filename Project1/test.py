import random

from exceptions import TooManyJobsException
from job import Job
from scheduler import FirstComeFirstServe, ShortestJobFirst, RoundRobin2, RoundRobin5

MAX_TIME = 20
TRIALS = 500
testlen = (5, 10, 15)
turnaround = [] #list of lists to store performance per algorithm per testlen

for l in range(len(testlen)): #per testing length
    turnaround.append([[],[],[],[]]) #hold 4 algorithms trial results
    for t in range(TRIALS): #per trial
        joblens = [random.randint(1, MAX_TIME) for i in testlen] #generate random list of size 5, 10, or 15

        jobs = [Job(i+1, i) for i in joblens]
        fcfs = FirstComeFirstServe(jobs)
        fcfs.run()
        turnaround[l][0].append(fcfs.avgTurnaround())

        jobs = [Job(i+1, i) for i in joblens]
        sjf = ShortestJobFirst(jobs)
        sjf.run()
        turnaround[l][1].append(sjf.avgTurnaround())

        jobs = [Job(i+1, i) for i in joblens]
        rr2 = RoundRobin2(jobs)
        rr2.run()
        turnaround[l][2].append(rr2.avgTurnaround())

        jobs = [Job(i+1, i) for i in joblens]
        rr5 = RoundRobin5(jobs)
        rr5.run()
        turnaround[l][3].append(rr5.avgTurnaround())


avgturnaround = [] #FCFS, SJF, RR2, RR5

for t in range(len(testlen)):
    turnaround.append([[],[],[],[]])
    for a in range(4):
        avgturnaround[t][a] = float('%.3f'%(sum(turnaround[t][a])/TRIALS))

print(avgturnaround)