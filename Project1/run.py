from generateJobs import generateJobs, readJobs
from job import Job
from scheduler import FirstComeFirstServe, ShortestJobFirst, RoundRobin

import numpy as np

NUM_JOBS = 5
if NUM_JOBS > 30:
    raise BaseException("Too many jobs") #project desc limits to 30 jobs
generateJobs(NUM_JOBS)