package org.proto1.workflow;


public interface ApproveDocumentService {
	void startApprove(Long documentID, Long approveTypeID);
	void startApproveByName(Long documentID, String approveTypeName);
}
