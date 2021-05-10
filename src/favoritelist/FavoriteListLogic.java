package favoritelist;

import java.util.ArrayList;

import bean.FavoriteBean;
import jdbc.FavoriteJdbc;

public class FavoriteListLogic {


	public ArrayList<FavoriteBean> favoriteGetLogic(int loginUserNo) {
		ArrayList<FavoriteBean> returnlist = new ArrayList<FavoriteBean>();

		FavoriteJdbc faveJdbc = new FavoriteJdbc();
		returnlist = faveJdbc.getFaves(loginUserNo);

		return returnlist;
	}

	public FavoriteBean deleteLogic(int favoriteNo) {
		FavoriteBean returnBean = null;
		FavoriteJdbc favoriteJdbc = new FavoriteJdbc();
		 returnBean = favoriteJdbc.daleteFaves(favoriteNo);
		    System.out.println(returnBean);

		return null;

	}


}
