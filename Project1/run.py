from exceptions import TooManyJobsException
from jobfile import generateJobs, readJobs
from job import Job

from fcfs import FirstComeFirstServe
from sjf import ShortestJobFirst
from rr2 import RoundRobin2
from rr5 import RoundRobin5

import numpy as np

NUM_JOBS = 5
if NUM_JOBS > 30:
    raise TooManyJobsException("Too many jobs") #project desc limits to 30 jobs
generateJobs(NUM_JOBS)