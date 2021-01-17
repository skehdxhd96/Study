package com.phonemall.spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.CategoryVO;
import org.zerock.domain.CustomerVO;
import org.zerock.domain.ProductVO;
import org.zerock.domain.testVO;
import org.zerock.mapper.CustomerMapper;
import org.zerock.mapper.ProductMapper;
import org.zerock.mapper.testMapper;
import org.zerock.service.KakaoService;
import org.zerock.utils.UploadFileUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Controller
@Log4j
@AllArgsConstructor
public class PhoneMallController {
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	//Controller는 Service에 의존적이므로 Mapper를 private로 만들어놓으면, AllArgsConstructor에 의해 생성자를 만들고, Autowired를 사용하지 않아도 자동으로 주입된다.
	private KakaoService kakaoService;
	private ProductMapper pm;
	private testMapper tm;
	private CustomerMapper cm;
	
	
	@RequestMapping("/")
	public String toMainPage(Model model){
		
		return "/mallView/mainPage";
	}
	
	@RequestMapping("/product")
	public String toProductPage(Model model) {
		
		model.addAttribute("product", pm.getList());
		
		return "/mallView/product";
	}
	
	@RequestMapping("/blog")
	public String toBlogPage() {
		return "/mallView/blog";
	}
	
	@RequestMapping("/about")
	public String toAboutPage() {
		return "/mallView/about";
	}
	
	@RequestMapping("/contact")
	public String toContackPage() {
		return "/mallView/contact";
	}
	
	@RequestMapping("/myPurchase")
	public String toMyAccount() {
		return "/mypage/myPurchase";
	}
	
	@RequestMapping("/wishList")
	public String toWishList() {
		return "/mypage/wishList";
	}
	
	
	@RequestMapping("/login")
	public String toLoginPage(@RequestParam(value = "code", required=false) String code, Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		String kakaoUrl = kakaoService.getAuthorizationUrl(session);
		model.addAttribute("kakaoUrl", kakaoUrl);
		
		if(code != null) {
	        String access_Token = kakaoService.getAccessToken(code);
	        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	        //CustomerVO c = new CustomerVO();
	        //c.setCustomer_id(Integer.valueOf(userInfo.get("id").toString()));
	        //c.setCustomer_email(userInfo.get("email").toString());
	        //c.setCustomer_name(userInfo.get("nickname").toString());
	        rttr.addFlashAttribute("id", Integer.valueOf(userInfo.get("id").toString()));
	        rttr.addFlashAttribute("email", userInfo.get("email").toString());
	        rttr.addFlashAttribute("name", userInfo.get("nickname").toString());
	        session.setAttribute("access_Token", access_Token);
	        
	        return "redirect:/memberplus";
		}else {
			return "/mypage/login";
		}
	}
	
	@GetMapping("/disconnect")
	public String DisConnect(@RequestParam(value = "code", required=false) String code, HttpServletRequest request) {
		//회원탈퇴랑 연관
		HttpSession session = request.getSession();
	    kakaoService.kakaoDisConnect((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "redirect:/";
	}
	
	@GetMapping("/logoutWithKakao")
	public String logoutWithKakao() {
		//로그아웃 with Kakao
		kakaoService.logoutWithKakao();
		
		return "redirect:/";
	}
	
	@GetMapping("/memberplus")
	public void memberplus() {
		
	}
	
	@PostMapping("/memberplus")
	public String memberplus(CustomerVO c) {
		
		cm.UserLogin(c);
		
		return "redirect:/";
	}
	
	@RequestMapping("/viewCart")
	public String toViewCart() {
		return "/mypage/viewCart";
	}
	
	@RequestMapping("/checkOut")
	public String toCheckOut() {
		return "/mypage/checkOut";
	}
	
	@RequestMapping("/myInfo")
	public String toMyInfo() {
		return "/mypage/myInfo";
	}
	
	@RequestMapping("/myInquiry")
	public String toMyInquiry() {
		return "/mypage/myInquiry";
	}
	
	@GetMapping("/register")
	public void RegisterPage() {

	}
	
	@PostMapping("/register")
	public String RegisterPage(ProductVO p) {
		
		pm.insert(p);
		
		return "redirect:/product";
	}
	
	// test
	
	@GetMapping("/test")
	public void test(Model model) {
		
		List<CategoryVO> category = null;
		category = tm.getCategory();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	@PostMapping("/test")
	public String test(testVO t, MultipartFile file) throws Exception{
		
		//이미지 등록 메소드
		String imgUploadPath = uploadPath + File.separator + "imgUpload"; // C:/upload/imgUpload
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath); // C:/upload/imgUpload/년/월/일 폴더 생성후 경로반환
		String fileName = null;

		
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") { //파일 인풋박스에 첨부된 파일이 없다면(=첨부된 파일이 이름이 없다면)
		 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else { // 파일이 없으면 none.png 생성
		 fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		System.out.println(imgUploadPath);
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getBytes());
		System.out.println(ymdPath);
		
		t.setTest_img(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		t.setTest_thumbnail(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		//이미지 등록 메소드 end
		
		tm.register(t);
		
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public void test_list(Model model) {
		
		model.addAttribute("test_list", tm.testlist());
	}
	
	@GetMapping("/test_view")
	public void getViewById(@RequestParam("id") int id, Model model) {
		
		log.info("get goods view");
		
		testVO t = tm.getById(id);
		
		model.addAttribute("goods", t);
	}
	
	@GetMapping("/test_modify")
	public void modify(@RequestParam("id") int id, Model model) {
		
		//일단 그 번호에 해당하는 값 불러오기
		testVO t = tm.getById(id);
		
		model.addAttribute("goods", t);
		
	}
	
	@PostMapping("/test_modify")
	public String modify(testVO t) {
		
		//수정 시작
		tm.testModify(t);
		
		return "redirect:/list";
	}
	
	@PostMapping("/delete")
	public String testDelete(int id) {
		
		tm.testDelete(id);
		
		return "redirect:/list";
	}
}
