package LibraryManager.BookLibraryManager.Dto;

import lombok.Data;

@Data
public class Structure<T> {
	private int statuscode;
	private T data;

}
