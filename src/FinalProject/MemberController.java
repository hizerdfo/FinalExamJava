package FinalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberController extends Controller{
    public int command;

    public MemberController(Scanner sc) {
        super(sc);
    }
    MemberManager mm = new MemberManager();

    public void doCommand() {
        switch(command) {
            case 0: //뒤로 구현
                System.out.println("뒤로 구현");
                break;
            case 1: //회원 조회
                System.out.println("회원 조회 구현");
                searchMembers(mm.members);
                break;
            case 2: //회원 등록
                System.out.println("회원 등록 구현");
                createMember(mm.members);
                break;
            case 3: //회원 수정
                System.out.println("회원 수정 구현");
                updateMember(mm.members);
                break;
            case 4: //회원 삭제
                System.out.println("회원 삭제 구현");
                deleteMember(mm.members);
                break;
            case 5: //삭제 취소
                System.out.println("삭제 취소 구현");
                deleteMember(mm.members);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    public void deleteMember(ArrayList<Member> members){
        System.out.println("삭제할 회원의 이름을 입력하세요");
        String name = sc.nextLine();
        for(int i = 0; i < members.size(); i++){
            if(name.equals(members.get(i).userName)){
                System.out.println("삭제 완료");
                Member deleteMember = members.remove(i);
                //삭제 취소
                System.out.println("0: 뒤로 가기 1: 삭제 취소");
                int cancel = sc.nextInt();
                if(cancel == 1){
                    members.add(deleteMember);
                    System.out.println("삭제 취소하였습니다.");
                }
                return;
            }
        }

    }
    private void searchMembers(ArrayList<Member> members) {
        System.out.println("1. 전체 회원  2:회원 검색");
        int result = sc.nextInt();
        sc.nextLine();


        if(result == 1){
            System.out.println("전체 회원 목록");
            for (Member member : members) {
                System.out.println("이름: " + member.getUserName());
                System.out.println("주소: " + member.getUserAddress());
                System.out.println("연락처: " + member.getPhoneNumber());
                System.out.println("등록일: " + member.getRegistrationDate());
                System.out.println("생년월일: " + member.getBirthDate());
                System.out.println("--------------------");
            }
        } else if (result == 2){
            System.out.println("검색할 회원의 이름을 입력하세요: ");
            String name = sc.nextLine();


        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    private void updateMember(ArrayList<Member> members) {
        System.out.println("회원의 이름을 입력하세요: ");
        String name = sc.nextLine();
        sc.nextLine();
        Member member = new Member();
        for (Member m : members) {
            if (m.getUserName().equals(name)) {
                member = m;
                break;
            }
        }

        if (member == null) {
            System.out.println("회원이 존재하지 않습니다.");
            return;
        }

        System.out.println("수정할 정보");
        System.out.println("1. 이름 2. 주소 3. 연락처 4. 생년월일");
        int result = sc.nextInt();
        sc.nextLine();
        switch (result) {
            case 1:
                System.out.print("수정할 이름을 입력하세요: ");
                String newName = sc.nextLine();
                member.setUserName(newName);
                System.out.println("변경 완료");
                break;
            case 2:
                System.out.print("수정할 주소를 입력하세요: ");
                String newAddress = sc.nextLine();
                member.setUserAddress(newAddress);
                System.out.println("변경 완료");
                break;
            case 3:
                System.out.print("수정할 연락처를 입력하세요: ");
                String newPhoneNumber = sc.nextLine();
                member.setPhoneNumber(newPhoneNumber);
                System.out.println("변경 완료");
                break;
            case 4:
                System.out.print("수정할 생일을 입력하세요(yyyy-MM-dd): ");
                String newBirthDate = sc.nextLine();
                member.birthDate(newBirthDate);
                System.out.println("변경 완료");
                break;
            default:
                System.out.println("입력 오류");
                break;
        }
    }


    private void createMember(ArrayList<Member> members) {
        Member member = new Member();

        System.out.println("회원 이름을 입력하세요.");
        member.setUserName(sc.next());
        sc.nextLine();
        System.out.println("회원 주소를 입력하세요.");
        member.setUserAddress(sc.nextLine());

        System.out.println("회원 전화번호를 입력하세요.");
        member.setPhoneNumber(sc.nextLine());

        System.out.println("회원 등록일을 입력하세요. (YYYY-MM-DD)");
        member.registrationDate(sc.nextLine());
        System.out.println("회원 생년월일을 입력하세요. (YYYY-MM-DD)");
        member.birthDate(sc.nextLine());
        mm.addMember(member);
        System.out.println("회원 등록이 완료되었습니다.");

    }





}
