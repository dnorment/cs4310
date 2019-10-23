import math

class Scheduler():
    """
    Class used to simulate the scheduling algorithms.

    Args:
        jobArray(list): a list of Job objects that contain job number, remaining time, and 'finished' boolean.
    """
    def __init__(self, jobArray):
        """
        Constructor function to initialize the Scheduler.

        Holds the jobArray, number of jobs, start and finish time of the burst, current job index, current time, and schedule table.
        """

        self.jobArray = jobArray
        self.numJobs = len(jobArray)
        self.burstStart = self.burstFinish = 0
        self.currentJob = 0
        self.currentTime = 0
        self.scheduleTable = []
    
    def avgTurnaround(self):
        """
        Function to get the average turnaround of the Scheduler after it has finished.

        Returns:
            int: Average turnaround time of the method.
        """

        return sum(job.finishedAt for job in self.jobArray) / self.numJobs

    def isFinished(self):
        """
        Function to determine if the Scheduler has worked on all jobs to completion.

        Returns:
            boolean: Whether the Scheduler has finished all jobs or not.
        """

        return all(job.finished for job in self.jobArray)
    
    def tick(self):
        """
        Function to increase the current time by 1.
        """

        self.currentTime += 1

    def run(self, maxBurstTime=math.inf):
        """
        Function to process each job in the jobArray until completion. It goes through the array, decreasing each remaining time by 1 until it is finished. It will skip over completed jobs and will move onto the next job if the current burst time is greater than the time allotted by a Round Robin mode for example. By default, it behaves in a first-come, first-serve fashion.

        Args:
            maxBurstTime(int): The maximum time allowed for the run() method to work on a job at one time. If exceeded, it will move on to the next job.
        """

        while not self.isFinished(): #work until finished
            currJob = self.jobArray[self.currentJob % self.numJobs] #get current job from list
            if not currJob.finished and (self.currentTime - self.burstStart < maxBurstTime): #if job unfinished and not exceeding max burst time
                self.tick()
                currJob.work(self.currentTime) #decrease remaining time, set finished if 0
                if currJob.finished:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish, currJob.jobNum]) #append job information and time finished
                    self.burstStart = self.burstFinish
            elif self.currentTime - self.burstStart >= maxBurstTime:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish]) #append job information
                    self.burstStart = self.burstFinish
                    self.currentJob += 1
            else:
                self.currentJob += 1


class FirstComeFirstServe(Scheduler):
    """
    A first-come, first-serve scheduler. Subclass of Scheduler.
    """

    pass


class ShortestJobFirst(FirstComeFirstServe):
    """
    A shortest job first scheduler. Subclass of Scheduler.
    """

    def run(self):
        """
        The overwritten run() function for a shortest job first scheduler. The jobs are sorted by remaining time then used in the superclass' run() function.
        """
        
        self.jobArray.sort() #sort by shortest remaining time first
        super().run()


class RoundRobin2(Scheduler):
    """
    A round-robin scheduler that works in bursts of 2. Subclass of Scheduler.
    """

    def run(self):
        """
        The overwritten run() function for a round robin scheduler of burst length 2. 
        """

        super().run(maxBurstTime=2)


class RoundRobin5(Scheduler):
    """
    A round-robin scheduler that works in bursts of 5. Subclass of Scheduler.
    """

    def run(self):
        """
        The overwritten run() function for a round robin scheduler of burst length 5. 
        """

        super().run(maxBurstTime=5)