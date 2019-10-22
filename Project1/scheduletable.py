from tabulate import tabulate

def table(data):
    return tabulate(data, headers=['Job Number', 'Start time', 'End time', 'Finished job'], tablefmt='github')