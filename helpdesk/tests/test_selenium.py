import os
import random
import time

from django.test import LiveServerTestCase
from selenium import webdriver
from selenium.webdriver import DesiredCapabilities
from selenium.webdriver.common.keys import Keys


class SeleniumTests(LiveServerTestCase):
    def setUp(self):
        self.selenium = webdriver.Remote("http://localhost:4444", DesiredCapabilities.FIREFOX)
        super(SeleniumTests, self).setUp()

    def tearDown(self):
        self.selenium.quit()
        super(SeleniumTests, self).tearDown()

    def test(self):
        selenium = self.selenium

        selenium.get("http://localhost:8080/login")

        print("In login page ...")

        username = selenium.find_element_by_name("username")
        password = selenium.find_element_by_name("password")

        print("filling username and password ...")
        username.sendKeys("se")
        password.sendKeys("se")

        form = selenium.find_element_by_tag_name("form")
        print("submitting the form ...")
        form.submit()

        time.sleep(5000)

        print("In add data ...")

        se = selenium.find_element_by_class_name("dropdown")

        settings = se.find_element_by_cssSelector("ul > li:nth-child(4)")
        print("Click on the profile ...")
        se.click()
        time.sleep(1000)
        print("Click on the system settings ...")
        settings.click()
        time.sleep(2000)

        MaintainQueues = selenium.find_element_by_link_text("Maintain Queues")
        print("Click on the Maintain Queues link ...")
        MaintainQueues.click()
        time.sleep(2000)

        AddQueue = selenium.find_element_by_class_name("addlink")
        print("Click on add item ...")
        AddQueue.click()
        time.sleep(2000)

        title = selenium.find_element_by_name("title")

        slug = selenium.find_element_by_name("slug")

        r = random.randint
        print("Filling the title and slug ...")
        title.sendKeys("queue_" + r)
        slug.sendKeys("slug")
        time.sleep(1000)

        form = selenium.find_element_by_id("queue_form")
        print("Submitting the form ...")
        form.submit()
        time.sleep(2000)

        print("Redirecting to the submitting ticket form ...")
        selenium.get("http://localhost:8080/tickets/submit/")

        select = selenium.find_element_by_id("id_queue")
        print("Filling the form ...")
        select.sendKeys("1")
        time.sleep(1000)

        r = random.randint

        titleTicket = selenium.find_element_by_id("id_title")
        titleTicket.sendKeys("ticket_" + r)
        time.sleep(1000)

        description = selenium.find_element_by_id("id_body")
        description.sendKeys("ticket_" + r)
        time.sleep(1000)

        formTicket = selenium.find_element_by_tag_name("form")
        print("Submitting the form ...")
        formTicket.submit()
        time.sleep(3000)

        se = selenium.find_element_by_class_name("dropdown")
        settings = se.find_element_by_cssSelector("ul > li:nth-child(5)")
        print("Click on the profile ...")
        se.click()
        time.sleep(1000)
        print("Click on the logout ...")
        settings.click()
        time.sleep(2000)
