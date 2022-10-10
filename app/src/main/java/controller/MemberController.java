package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Member;
import model.Member.MutableMember;
import model.MemberList;
import model.MembersItemList;
import view.MemberView.EditMemberChoices;
import view.MemberView.MemberMenuChoices;

/**
 * Representing a controller for Member.
 */
public class MemberController {
  private view.MemberView memberUi = new view.MemberView();
  private model.MemberList memberList = new MemberList();
  model.Member.MutableMember selectedMember = null;

  /**
  * For changing, deleting, adding and representing.
  *
  * @throws Exception Error.
  */
  public void selectMemberToActAs() throws Exception {
    selectedMember = memberUi.selectMember(memberList.getMembers());
  }

  /**
  * For referencing.
  *
  * @return Object.
  * 
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Member.MutableMember getSelectedMember() {
    return selectedMember;
  }

  /**
  * For logging out.
  */
  public void removeSelectedMember() {
    selectedMember = null;
  }

  /**
  * For referencing members.
  *
  * @return Object.
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public MemberList getMemberList() {
    return memberList;
  }


  /**
  * For editing member.
  */
  public void memberMenu() {
    MemberMenuChoices action = memberUi.showMemberMenu(selectedMember);
    switch (action) {
      case InspectMember:
        memberUi.showMember(selectedMember);
        break;
      case EditMember:
        editMemberMenu();
        break;
      case DeleteMember:
        deleteMember(selectedMember);
        break;
      case Return:
        return;
      default:
        return;
    }
  }

  /**
  * Edit fields of member.
  */
  public void editMemberMenu() {
    EditMemberChoices action = memberUi.editMember();
    switch (action) {
      case Name:
        editName(selectedMember);
        break;
      case PhoneNumber:
        editPhoneNumber(selectedMember);
        break;
      case Email:
        editEmail(selectedMember);
        break;
      default:
        editName(selectedMember);
    }
  }

  /**
  * New object.
  *
  * @param time Object.
  *
  * @throws Exception Error.
  */
  public void createMember(model.Time time) throws Exception {
    try {
      model.Member newMember = memberUi.createMember();
      duplicatePhoneNumberCheck(newMember.getPhoneNumber());
      duplicateEmailCheck(newMember.getEmail());
      newMember.setDayOfCreation(time.getCurrentDay());
      model.Member createdMember = memberList.addMember(newMember);
      model.MembersItemList itemList = new MembersItemList();
      itemList.setOwner((MutableMember) createdMember);
      createdMember.setItemList(itemList);
      memberUi.showMember(createdMember);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * To make phoneNumber unique.
  *
  * @param phoneNumber String.
  * @throws Exception If duplicate.
  */
  public void duplicatePhoneNumberCheck(String phoneNumber) throws Exception {
    for (Member m : memberList.getMembers()) {
      if (m.getPhoneNumber().equals(phoneNumber)) {
        throw new Exception("Invalid phonenumber");
      } 
    }
  }

  /**
  * To make email unique.
  *
  * @param email String
  * @throws Exception If duplicate.
  */
  public void duplicateEmailCheck(String email) throws Exception {
    for (Member m : memberList.getMembers()) {
      if (m.getEmail().equals(email)) {
        throw new Exception("Invalid email");
      } 
    }
  }

  /**
  * Removes reference to MutableMember.
  *
  * @param selectedMember Object.
  */
  public void deleteMember(Member.MutableMember selectedMember) {
    String deleteAnswer = memberUi.deleteMember(selectedMember);
    if (deleteAnswer.equals("Y") || deleteAnswer.equals("y")) {
      memberList.deleteMember(selectedMember);
      removeSelectedMember();
      memberUi.succesfulAction();
    } else {
      return;
    }
  }

  /**
  * Edit namefield.
  *
  * @param selectedMember Object.
  */
  public void editName(Member.MutableMember selectedMember) {
    String newName = memberUi.newName();
    selectedMember.setName(newName);
  }

  /**
  * Edit phoneNumberfield.
  *
  * @param selectedMember Object.
  */
  public void editPhoneNumber(Member.MutableMember selectedMember) {
    String newPhoneNumber = memberUi.newPhoneNumber();
    try {
      duplicatePhoneNumberCheck(newPhoneNumber);
      selectedMember.setPhoneNumber(newPhoneNumber);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
  * Edit emailfield.
  *
  * @param selectedMember Object.
  */
  public void editEmail(Member.MutableMember selectedMember) {
    String newEmail = memberUi.newEmail();
    try {
      duplicateEmailCheck(newEmail);
      selectedMember.setEmail(newEmail);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
  * More member info, no item info.
  */
  public void showMembersSimple() {
    memberUi.displayAllMembersSimple(memberList.getMembers());
  }

  /**
  * Less member info, all item info.
  */
  public void displayMembersVerbose() {
    memberUi.displayAllMembersVerbose(memberList.getMembers());
  }
}
