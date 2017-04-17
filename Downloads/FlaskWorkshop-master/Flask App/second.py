import urllib2
import re
import requests
import string
from bs4 import BeautifulSoup
from urllib2 import urlopen

def scrape(url):
	pages=[]
	source_code = requests.get(url) # Get source Code
	plain_text = source_code.text
	soup = BeautifulSoup(plain_text, "html.parser")
	for link in soup.findAll('a'):
		href = str(link.get("href")) # Gets actual link
		if "bing" not in href and "go.microsoft.com" not in href and "http://www.freebase.com" not in href:
			if href.startswith("http"):
				if href not in pages:
					pages.append(href)
#		print href
	return pages

def getSearch(name):
	name = name.replace(" ", "+")
	url="http://www.bing.com/search?q="+name+"&qs=n&form=QBLH&sp=-1&pq="+name+"&sc=9-6&sk=&cvid=8B19E9B4B0524A9C8E2F34B20E2D0C7F"
	return scrape(url)
#print getSearch("smtown")

arr = getSearch("shinee")
for i in arr:
	print i


def scrapeVideo(url):
	pages=[]
	source_code = requests.get(url) # Get source Code
	plain_text = source_code.text
	soup = BeautifulSoup(plain_text, "html.parser")
	for link in soup.findAll('a'):
		href = str(link.get("href")) # Gets actual link
		if "bing" not in href and "go.microsoft.com" not in href and "http://www.freebase.com" not in href:
			if href not in pages:
				pages.append(href)
#		print href
	return pages

def getSearchVideo(name):
	name = name.replace(" ", "+")
	url="https://www.youtube.com/results?search_query="+name
	arr=scrapeVideo(url)
	results = []
	for i in arr:
		if "/watch" in i:
			if i not in results:
				results.append(i.replace("/watch?v=", ""))
	return results
#print getSearch("smtown")

arr = getSearchVideo("shinee")
for i in arr:
	print i
