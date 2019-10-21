from scheduler import Scheduler

class RoundRobin2(Scheduler):

    def run(self):
        super().run(maxBurstTime=2)