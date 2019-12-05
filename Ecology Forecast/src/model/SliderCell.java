package model;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class SliderCell<S> extends TableCell<S, Slider>{
	private final Slider sli;
	
	public SliderCell(int min, int max, int value) {
		this.sli = new Slider(0,500,200);
		this.sli.setMaxWidth(150);
		
	}
	
	public S getCurrentItem()
	{
		return (S) getTableView().getItems().get(getIndex());
	}
	
	public static <S> Callback<TableColumn<S, Slider>, TableCell<S, Slider>> forTableColumn(int min, int max, int value)
	{
		return param -> new SliderCell<> (min, max, value);
	}
	
	@Override
	public void updateItem(Slider item, boolean empty)
	{
		super.updateItem(item, empty);
		
		if(empty)
		{
			setGraphic(null);
		}
		else
		{
			setGraphic(sli);
		}
	}

}
