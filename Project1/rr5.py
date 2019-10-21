from scheduler import Scheduler

class RoundRobin5(Scheduler):

    def run(self):
        super().run(maxBurstTime=5)