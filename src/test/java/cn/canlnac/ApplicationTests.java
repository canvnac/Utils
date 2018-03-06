package cn.canlnac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		lTOr(3,3,0,0);
	}

	static final int n = 2;

	public void lTOr(int lu, int le, int ru, int re) {
		if (le>=lu && re>=ru) {
			le--;lu--;
			re++;ru++;
			System.out.println("一个英国人和一个美国人过右岸---左岸：英国"+le+"人，美国"+lu+"人；右岸：英国"+re+"人，美国"+ru+"人");
		}
		if (le+lu > 0)
			rTOl(lu, le, ru, re);
	}

	public void rTOl(int lu, int le, int ru, int re) {
		if (le>=lu && re>=ru) {
			le++;re--;
			System.out.println("一个英国人过左岸---左岸：英国"+le+"人，美国"+lu+"人；右岸：英国"+re+"人，美国"+ru+"人");
		}
		if (le+lu > 0)
			lTOr(lu, le, ru, re);
	}

}
