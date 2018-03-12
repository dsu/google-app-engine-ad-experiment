package com.example;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	@RequestMapping("/")
	public String zgoda(@CookieValue(value = "enabled", required = false) String enabledCookieVal) {

		if (enabledCookieVal != null) {
			return "already";
		}

		return "zgoda";
	}

	@GetMapping("/intro")
	public String intro(@CookieValue(value = "enabled", required = false) String enabledCookieVal) {

		if (enabledCookieVal != null) {
			return "already";
		}

		return "intro";
	}

	@GetMapping("/tekst")
	public String tekst(HttpServletResponse response, Model model,
			@CookieValue(value = "enabled", required = false) String enabledCookieVal) {

		if (enabledCookieVal == null) {
			enabledCookieVal = String.valueOf(getRand());
			response.addCookie(new Cookie("enabled", enabledCookieVal));
		}

		if (enabledCookieVal.equals("1")) {
			model.addAttribute("ad", true);
		} else {
			model.addAttribute("ad", false);
		}

		return "tekst-teklamy";
	}

	public static int getRand() {

		Random random = new Random();
		boolean res = random.nextBoolean();

		if (res)
			return 1;
		else
			return 0;

	}

}