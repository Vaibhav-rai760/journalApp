package com.vaibhavspringboot.journalApp.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
	
    @Id
	private ObjectId id;
	
    @NonNull
	private String title;
	
	private String Content;
	
	public LocalDateTime getDate() {
		return date;
	}
	
	private LocalDateTime date;
	
	
//	public void setDate(LocalDateTime localDateTime) {
//		this.date = localDateTime;
//	}
//	public ObjectId getId() {
//		return id;
//	}
//	public void setId(ObjectId id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getContent() {
//		return Content;
//	}
//	public void setContent(String content) {
//		Content = content;
//	}
	
}
