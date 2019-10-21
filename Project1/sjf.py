from fcfs import FirstComeFirstServe

class ShortestJobFirst(FirstComeFirstServe):

    def run(self):
        self.jobArray.sort() #sort by shortest remaining time first
        super().run()