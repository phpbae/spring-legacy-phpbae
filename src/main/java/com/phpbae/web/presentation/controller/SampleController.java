package com.phpbae.web.presentation.controller;

import com.phpbae.web.business.service.SampleService;
import com.phpbae.web.business.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;


    /**
     * tomcat root path = / 이걸로 지정이 되어있다.
     * return type 이 void 인경우는, 현재 경로(context path)에 해당하는 JSP 파일을 실행. 파일명은 요청한 URI명 -> doA.jsp
     * return void
     */
    @RequestMapping(value = "doA", method = RequestMethod.GET)
    public void doA() {
        System.out.println("doA call..");
    }

    @RequestMapping(value = "doB", method = RequestMethod.GET)
    public void doB() {
        System.out.println("doB call..");
    }

    /**
     * 리턴된 문자열로 jsp 파일을 찾아서 실행합니다.
     * 요청 URI : /sample/doC?msg=test
     *
     * @return String
     */
    @RequestMapping(value = "doC", method = RequestMethod.GET)
    public String doC(@ModelAttribute(name = "msg") String msg) {
        System.out.println("doC call..");
        return "sample/result";
    }

    //@ModelAttribute name을 지정안하면 어떻게 될까? msg에 값은 비어있고, view에는 공란으로 찍히는데, 값이 없으니 당연한거 같다.
    @RequestMapping(value = "doD", method = RequestMethod.GET)
    public ModelAndView doD(@ModelAttribute(name = "msg") String msg) {
        System.out.println("doD call..");
        ModelAndView mav = new ModelAndView("sample/result");
        return mav;
    }

    @RequestMapping(value = "doE", method = RequestMethod.GET)
    public String doE(Model model) {
        System.out.println("doE call..");

        //만들어진 객체 또는 결과물을 전달해야하는 경우
        MemberVO memberVO = new MemberVO("전설", 10, "남");

        model.addAttribute("member", memberVO);
        model.addAttribute(memberVO); //이름을 지정하지 않으면, 객체에 앞글자를 소문자로 처리한 이름으로 지정해준다.
        return "sample/result";
    }

    /**
     * redirect 하는 경우, 임시로 값을 같이 포함시켜서 전송이 가능하다.
     * RedirectAttributes 클래스를 파라미터로 사용하게 되면, 리다이렉트 시점에 원하는 데이터를 임시로 추가해서 넘기는 작업이 가능.
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "doF", method = RequestMethod.GET)
    public String doF(RedirectAttributes redirectAttributes) {
        System.out.println("doF call..");
        MemberVO memberVO = new MemberVO("전설", 10, "남");
        redirectAttributes.addFlashAttribute("member", memberVO);
        redirectAttributes.addFlashAttribute("msg", "안녕하세요!!!"); //addFlash 는 URI 뒤에 쿼리스트링형태로 붙지는 않는다. 세션에 저장 후, 리다이렉트 후 소멸한다.

        return "redirect:/sample/doG"; // redirect: 이 문자열을 이용하여 리다이렉트 시킨다.
    }

    @RequestMapping(value = "doG", method = RequestMethod.GET)
    public void doG(Model model) {
        System.out.println("doG call..");
        MemberVO memberVO = (MemberVO) model.asMap().get("member"); //Model 로 이렇게 꺼내서 사용이 가능하다.
        String msg = (String) model.asMap().get("msg");
        System.out.println(memberVO.toString());
        System.out.println(msg);
    }

    /**
     * JSON 형식으로 데이터를 받을 수 있다.
     * POM.XML에 jackson-core / jackson-databind 의존성을 추가해주자.
     *
     * @return ResponseEntity<T>
     */
    @RequestMapping(value = "doH", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity doH() {
        MemberVO memberVO = new MemberVO("JSON", 10, "남");
        return new ResponseEntity(memberVO, HttpStatus.OK);
    }

    /**
     * produces 속성을 사용하면, HTTP Content-Type을 지정한 속성으로 반환. 생략 시, 리턴 타입에 따라 자동으로 Content-Type 을 반환한다.
     *
     * @return
     */
    @RequestMapping(value = "doI", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<MemberVO> doI() {
        List<MemberVO> memberVOList = new ArrayList();
        memberVOList.add(new MemberVO("JSON", 20, "여"));
        memberVOList.add(new MemberVO("JSON2세", 30, "남"));
        memberVOList.add(new MemberVO("JavaScript Object Notation (데이터 표기법)", 50, "남"));
        return memberVOList;
    }

    @RequestMapping(value = "jdbc", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity sampleJdbc() {
        return new ResponseEntity(sampleService.getSampleData(), HttpStatus.OK);
    }

    @RequestMapping(value = "jpa/owner", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity sampleJpaOwner() {
        return new ResponseEntity(sampleService.getJpaDataOwner(), HttpStatus.OK);
    }

    @RequestMapping(value = "jpa/pet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity sampleJpaPet() {
        return new ResponseEntity(sampleService.getJpaDataPet(), HttpStatus.OK);
    }
}
