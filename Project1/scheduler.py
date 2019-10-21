from abc import ABC, abstractmethod
import numpy as np

class Scheduler(ABC):
    
    currentTime = 0

    def __init__(self, jobArray):
        self.jobArray = jobArray
    
    def isFinished(self):
        return all(job.finished for job in self.jobArray)
    
    @abstractmethod
    def tick(self):
        pass


class FirstComeFirstServe(Scheduler):
    
    def tick(self):
        pass


class ShortestJobFirst(Scheduler):
    
    def tick(self):
        pass


class RoundRobin(Scheduler):
    
    def tick(self):
        pass