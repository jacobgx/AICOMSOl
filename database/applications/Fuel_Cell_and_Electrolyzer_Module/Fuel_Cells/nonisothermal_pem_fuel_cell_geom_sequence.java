/*
 * nonisothermal_pem_fuel_cell_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class nonisothermal_pem_fuel_cell_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m_th", "0.15[mm]", "\u7f51\u539a\u5ea6");
    model.param().set("m_sw", "0.6[mm]", "\u7f51\u978d\u5bbd\u5ea6");
    model.param().set("m_sl", "0.3[mm]", "\u7f51\u978d\u659c\u5761\u957f\u5ea6");
    model.param().set("m_sfc", "0.4[mm]", "\u7f51\u978d\u5e73\u9762\u63a5\u89e6");
    model.param().set("m_sr", "0.3[mm]", "\u7f51\u72b6\u978d\u810a");
    model.param().set("m_osl", "0.8[mm]", "\u7f51\u5b54\u659c\u5761\u957f\u5ea6");
    model.param()
         .set("uc_vs", "m_sfc+m_sl+m_th/sqrt(m_sl^2+m_sr^2)*m_sr+m_sr*mdp", "\u7535\u6c60\u5355\u5143\u5782\u76f4\u504f\u79fb");
    model.param().set("uc_hs", "m_osl+m_sw", "\u7535\u6c60\u5355\u5143\u6c34\u5e73\u504f\u79fb");
    model.param().set("n_vc", "5", "\u5782\u76f4\u7535\u6c60\u5355\u5143\u6570");
    model.param().set("n_hc", "2", "\u6c34\u5e73\u7535\u6c60\u5355\u5143\u6570");
    model.param().set("c_th", "0.2[mm]", "\u9634\u6781\u539a\u5ea6");
    model.param().set("me_th", "0.05[mm]", "\u819c\u539a\u5ea6");
    model.param().set("a_th", "0.2[mm]", "\u9633\u6781\u539a\u5ea6");
    model.param().set("cp_th", "0.075[mm]", "\u51b7\u5374\u677f\u539a\u5ea6");
    model.param().set("ch_h", "0.6[mm]", "\u51b7\u5374\u901a\u9053\u9ad8\u5ea6");
    model.param().set("mdp", "0.2[1]", "\u7f51\u683c\u8ddd\u79bb\u53c2\u6570\uff0c0<=mdp<=1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "m_sr", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "m_sl", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "m_sl+m_sfc", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("upthick", "m_th");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("convexcorner", "tangent");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("thi1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "m_sw/2", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "m_sw/2+m_osl");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "m_sr", 0, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "-m_sl", 1, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "2*m_sr", 1, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "-(m_sfc+m_sl)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "2*m_sr", 2, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("thi1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("thi1").set("upthick", "m_th");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("thi1").set("convexcorner", "tangent");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("thi1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mov1").selection("input").set("thi1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mov1")
         .set("displx", "m_th/sqrt(m_sl^2+m_sr^2)*m_sr");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mov1")
         .set("disply", "m_th/sqrt(m_sl^2+m_sr^2)*m_sl");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mov2").set("displx", "uc_vs");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mov2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("mov2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("loft1", "Loft");
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").set("ext1", 8);
    model.component("comp1").geom("geom1").feature("loft1").selection("endprofile").set("wp2", 1);
    model.component("comp1").geom("geom1").run("loft1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("loft1", 15);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "m_sw/2", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("mir1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "uc_hs");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-uc_vs");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext2", "mov1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u96c6\u6d41\u4f53\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1");
    model.component("comp1").geom("geom1").feature("sel1").set("selshow", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u96c6\u6d41\u4f53\u9634\u6781 GDL \u63a5\u89e6");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni1", 11, 36);
    model.component("comp1").geom("geom1").feature("sel2").set("selshow", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u9634\u6781\u63a5\u89e6");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "n_vc > 1");
    model.component("comp1").geom("geom1").run("if1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "n_vc", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "-2*uc_vs", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "n_hc > 0.5");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"uc_hs", "0", "0"});
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").run("endif2");
    model.component("comp1").geom("geom1").create("if3", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif3", "EndIf", "if3");
    model.component("comp1").geom("geom1").feature("if3").set("condition", "n_hc > 1");
    model.component("comp1").geom("geom1").run("if3");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("sel1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"n_hc", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"2*uc_hs", "0", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").run("endif3");
    model.component("comp1").geom("geom1").create("if4", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif4", "EndIf", "if4");
    model.component("comp1").geom("geom1").feature("if4").set("condition", "n_hc<1");
    model.component("comp1").geom("geom1").run("if4");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"uc_hs", "2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"0", "-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3")
         .setIndex("distance", "2*m_sr+m_th/sqrt(m_sl^2+m_sr^2)*m_sl", 0);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u9634\u6781\u6c14\u5ba4\u548c\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u9634\u6781\u4e0a\u8868\u9762 1");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("ext3", 3);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u9634\u6781 GDL \u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel3").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif1").set("condition", "n_hc >= 1");
    model.component("comp1").geom("geom1").run("elseif1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"n_hc*2*uc_hs", "1"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .setIndex("size", "2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("pos", new String[]{"0", "-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4")
         .setIndex("distance", "2*m_sr+m_th/sqrt(m_sl^2+m_sr^2)*m_sl", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u9634\u6781\u4e0a\u8868\u9762 2");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("ext4", 3);
    model.component("comp1").geom("geom1").feature("sel4").set("selshow", false);
    model.component("comp1").geom("geom1").feature("sel4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").run("endif4");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").named("csel4");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "c_th", 0);
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "c_th+me_th/2", 1);
    model.component("comp1").geom("geom1").feature("ext5").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext5").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext5").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("ext5", "uni2");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "ext5*uni2+ext5");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u9634\u6781\u4fa7");
    model.component("comp1").geom("geom1").feature("co1").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").named("csel5");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u9634\u6781\u81ea\u7531\u6c14\u5ba4");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"csel3"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"csel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9634\u6781\u8fdb\u6c14\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel1").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").feature("boxsel1")
         .set("ymin", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel1")
         .set("ymax", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u9634\u6781\u51fa\u6c14\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel2").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").feature("boxsel2")
         .set("ymin", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel2")
         .set("ymax", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u819c\u4e2d\u9762");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "-c_th-me_th*0.51");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "-c_th-me_th*0.49");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").named("csel5");
    model.component("comp1").geom("geom1").feature("uni3").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").named("boxsel3");
    model.component("comp1").geom("geom1").feature("ext6").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "me_th/2", 0);
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "a_th+me_th/2", 1);
    model.component("comp1").geom("geom1").feature("ext6").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext6").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext6").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u819c\u4fa7\u9762");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ext6", 8);
    model.component("comp1").geom("geom1").feature("sel5").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp5").selection("face").named("sel5");
    model.component("comp1").geom("geom1").feature("wp5").set("origin", "boxcorner");
    model.component("comp1").geom("geom1").feature("wp5").set("faceparallelaxis", "s2");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").setIndex("table", "a_th", 0, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "(m_osl+m_sw)/4", 1, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").setIndex("table", "a_th", 1, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "(m_osl+m_sw)*3/4", 2, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "a_th+ch_h", 2, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "m_sw+m_osl", 3, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "a_th+ch_h", 3, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("thi1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("thi1").set("upthick", "m_th");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("thi1").set("convexcorner", "tangent");
    model.component("comp1").geom("geom1").feature("wp5").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp5").geom().selection("csel1")
         .label("\u9988\u6d41\u4f53\u5256\u9762");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("thi1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("thi1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("if1").set("condition", "n_hc > 0.5");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1")
         .set("pos", new String[]{"m_sw+m_osl", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("endif1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("if2", "If");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("if2").set("condition", "n_hc > 1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("if2");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1")
         .set("fullsize", new String[]{"n_hc", "1"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1")
         .set("displ", new String[]{"2*(m_sw+m_osl)", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("endif2");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").label("\u9633\u6781\u9988\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("ext7")
         .setIndex("distance", "-1*(2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)", 0);
    model.component("comp1").geom("geom1").feature("ext7").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext7");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u9633\u6781\u9988\u6d41\u4f53\u63a5\u89e6");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", "-(c_th+me_th+a_th+ch_h+m_th)*1.01");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "-(c_th+me_th+a_th+ch_h+m_th)*0.99");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp6").selection("face").named("sel5");
    model.component("comp1").geom("geom1").feature("wp6").set("origin", "boxcorner");
    model.component("comp1").geom("geom1").feature("wp6").set("faceparallelaxis", "s2");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("if1", "If");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("wp6").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("if1").set("condition", "n_hc < 1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"m_sw+m_osl", "ch_h+m_th"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("pos", new String[]{"0", "a_th"});
    model.component("comp1").geom("geom1").feature("wp6").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("endif1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("if2", "If");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("if2").set("condition", "n_hc >= 1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r2")
         .set("size", new String[]{"2*n_hc*(m_sw+m_osl)", "1"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r2").setIndex("size", "ch_h+m_th", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r2").set("pos", new String[]{"0", "a_th"});
    model.component("comp1").geom("geom1").feature("wp6").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("endif2");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext8", "Extrude");
    model.component("comp1").geom("geom1").feature("ext8")
         .setIndex("distance", "-1*(2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)", 0);
    model.component("comp1").geom("geom1").run("ext8");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u9633\u6781\u4fa7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmax", "-c_th-me_th/4");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("uni4", "Union");
    model.component("comp1").geom("geom1").feature("uni4").selection("input").named("boxsel5");
    model.component("comp1").geom("geom1").run("uni4");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp7").selection("face").named("sel5");
    model.component("comp1").geom("geom1").feature("wp7").set("origin", "boxcorner");
    model.component("comp1").geom("geom1").feature("wp7").set("faceparallelaxis", "s2");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1")
         .set("size", new String[]{"m_sw+m_osl", "m_th/2"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1")
         .set("pos", new String[]{"0", "-1*(2*m_sr+m_th/sqrt(m_sl^2+m_sr^2)*m_sl+a_th+c_th+me_th+cp_th)+a_th"});
    model.component("comp1").geom("geom1").feature("wp7").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp7").geom().selection("csel1")
         .label("\u51b7\u5374\u677f\u5256\u9762");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("if1").set("condition", "n_hc > 0.5");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mir1")
         .set("pos", new String[]{"m_sw+m_osl", "0"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp7").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").selection("input")
         .set("mir1", "r1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("endif1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("if2", "If");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("if2").set("condition", "n_hc > 1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("arr1")
         .set("fullsize", new String[]{"n_hc", "1"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("arr1")
         .set("displ", new String[]{"2*(m_sw+m_osl)", "0"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp7").geom().selection("csel2")
         .label("\u9634\u6781\u51b7\u5374\u901a\u9053\u677f\u5256\u9762");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature().create("ext9", "Extrude");
    model.component("comp1").geom("geom1").feature("ext9").label("\u9876\u677f");
    model.component("comp1").geom("geom1").feature("ext9")
         .setIndex("distance", "-1*(2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)", 0);
    model.component("comp1").geom("geom1").feature("ext9").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext9");
    model.component("comp1").geom("geom1").create("uni5", "Union");
    model.component("comp1").geom("geom1").feature("uni5").selection("input").set("ext9", "uni3");
    model.component("comp1").geom("geom1").run("uni5");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").label("\u9633\u6781\u81ea\u7531\u6c14\u5ba4");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", "-(c_th+me_th+a_th+ch_h+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmax", "-(c_th+me_th+a_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").label("\u9633\u6781\u8fdb\u6c14\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel7")
         .set("ymin", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel7")
         .set("ymax", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmin", "-(c_th+me_th+a_th+ch_h+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmax", "-(c_th+me_th+a_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").create("boxsel8", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel8").label("\u9633\u6781\u51fa\u6c14\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel8").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel8")
         .set("ymin", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel8")
         .set("ymax", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmin", "-(c_th+me_th+a_th+ch_h+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmax", "-(c_th+me_th+a_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel8").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel8");
    model.component("comp1").geom("geom1").create("boxsel9", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel9").label("\u51b7\u5374\u901a\u9053");
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmax", "-(c_th+me_th+a_th+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel9").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel9");
    model.component("comp1").geom("geom1").create("boxsel10", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel10").label("\u51b7\u5374\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel10").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel10")
         .set("ymin", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel10")
         .set("ymax", "(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmax", "-(c_th+me_th+a_th+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel10").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel10");
    model.component("comp1").geom("geom1").create("boxsel11", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel11").label("\u51b7\u5374\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel11").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel11")
         .set("ymin", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*0.99");
    model.component("comp1").geom("geom1").feature("boxsel11")
         .set("ymax", "((2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr)+(-2*n_vc*uc_vs-m_th/sqrt(m_sl^2+m_sr^2)*m_sr+(uc_vs+2*m_th/sqrt(m_sl^2+m_sr^2)*m_sr)))*1.01");
    model.component("comp1").geom("geom1").feature("boxsel11").set("zmax", "-(c_th+me_th+a_th+m_th/2)");
    model.component("comp1").geom("geom1").feature("boxsel11").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel11");
    model.component("comp1").geom("geom1").create("boxsel12", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel12").label("\u9876\u677f\u8868\u9762");
    model.component("comp1").geom("geom1").feature("boxsel12")
         .set("zmin", "0.99*((2*m_sr+m_th/sqrt(m_sl^2+m_sr^2)*m_sl+cp_th))");
    model.component("comp1").geom("geom1").feature("boxsel12").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel12")
         .set("zmax", "1.01*((2*m_sr+m_th/sqrt(m_sl^2+m_sr^2)*m_sl+cp_th))");
    model.component("comp1").geom("geom1").feature("boxsel12").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel12");
    model.component("comp1").geom("geom1").create("boxsel13", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel13").label("\u819c");
    model.component("comp1").geom("geom1").feature("boxsel13").set("zmin", "-c_th-me_th*0.99");
    model.component("comp1").geom("geom1").feature("boxsel13").set("zmax", "-c_th-me_th*0.01");
    model.component("comp1").geom("geom1").run("boxsel13");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel14", "boxsel13");
    model.component("comp1").geom("geom1").feature("boxsel14").label("\u9634\u6781 GDL");
    model.component("comp1").geom("geom1").feature("boxsel14").set("zmin", "-c_th*0.99");
    model.component("comp1").geom("geom1").feature("boxsel14").set("zmax", "-c_th*0.01");
    model.component("comp1").geom("geom1").run("boxsel14");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel15", "boxsel14");
    model.component("comp1").geom("geom1").feature("boxsel15").label("\u9633\u6781 GDL");
    model.component("comp1").geom("geom1").feature("boxsel15").set("zmin", "-(c_th+me_th)-a_th*0.99");
    model.component("comp1").geom("geom1").feature("boxsel15").set("zmax", "-(c_th+me_th)-a_th*0.01");
    model.component("comp1").geom("geom1").run("boxsel15");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel16", "boxsel15");
    model.component("comp1").geom("geom1").feature("boxsel16").label("\u9634\u6781 GDE");
    model.component("comp1").geom("geom1").feature("boxsel16").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel16").set("zmin", "-c_th-me_th*0.01");
    model.component("comp1").geom("geom1").feature("boxsel16").set("zmax", "-c_th*0.99");
    model.component("comp1").geom("geom1").feature("boxsel16").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel16");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel17", "boxsel16");
    model.component("comp1").geom("geom1").feature("boxsel17").label("\u9633\u6781 GDE");
    model.component("comp1").geom("geom1").feature("boxsel17").set("zmin", "-(c_th+me_th)-a_th*0.01");
    model.component("comp1").geom("geom1").feature("boxsel17").set("zmax", "-c_th-me_th*0.99");
    model.component("comp1").geom("geom1").run("boxsel17");
    model.component("comp1").geom("geom1").create("boxsel18", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel18").label("\u4e0b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel18").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel18").set("zmax", "-(c_th+me_th+a_th+m_th+ch_h*0.99)");
    model.component("comp1").geom("geom1").feature("boxsel18").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel18");
    model.component("comp1").geom("geom1").create("boxsel19", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel19").label("\u53f3\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel19").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel19").set("xmin", "2*(m_sw+m_osl)*n_hc-m_th*0.1");
    model.component("comp1").geom("geom1").feature("boxsel19").set("xmax", "2*(m_sw+m_osl)*n_hc+m_th*0.1");
    model.component("comp1").geom("geom1").feature("boxsel19").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel19");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel20", "boxsel19");
    model.component("comp1").geom("geom1").feature("boxsel20").label("\u5de6\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel20").set("xmin", "-m_th*0.1");
    model.component("comp1").geom("geom1").feature("boxsel20").set("xmax", "m_th*0.1");
    model.component("comp1").geom("geom1").run("boxsel20");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1")
         .label("\u71c3\u6599\u7535\u6c60\u7269\u7406\u573a\u57df");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"csel3", "ext7", "ext9", "boxsel6", "boxsel13", "boxsel14", "boxsel15"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u4e0a\u4e0b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"boxsel12", "boxsel18"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").feature().duplicate("unisel3", "unisel2");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u5de6\u53f3\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"boxsel19", "boxsel20"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u91d1\u5c5e\u5bfc\u4f53");
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"csel1", "ext7", "ext9"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u81ea\u7531\u6c14\u4f53\u57df");
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"difsel1", "boxsel6"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6")
         .label("\u9633\u6781\u81ea\u7531\u6c14\u4f53\u548c GDL");
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"boxsel6", "boxsel15"});
    model.component("comp1").geom("geom1").feature().duplicate("unisel7", "unisel6");
    model.component("comp1").geom("geom1").feature("unisel7")
         .label("\u9634\u6781\u81ea\u7531\u6c14\u4f53\u548c GDL");
    model.component("comp1").geom("geom1").runPre("unisel7");
    model.component("comp1").geom("geom1").feature("unisel7").set("input", new String[]{"difsel1", "boxsel14"});
    model.component("comp1").geom("geom1").run("unisel7");
    model.component("comp1").geom("geom1").create("unisel8", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel8").label("GDL");
    model.component("comp1").geom("geom1").feature("unisel8").set("input", new String[]{"boxsel14", "boxsel15"});
    model.component("comp1").geom("geom1").run("unisel8");
    model.component("comp1").geom("geom1").create("unisel9", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel9").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("unisel9").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel9")
         .set("input", new String[]{"boxsel1", "boxsel7", "boxsel10"});
    model.component("comp1").geom("geom1").run("unisel9");
    model.component("comp1").geom("geom1").create("unisel10", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel10").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("unisel10").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel10")
         .set("input", new String[]{"boxsel2", "boxsel8", "boxsel11"});
    model.component("comp1").geom("geom1").run("unisel10");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("GDL \u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel8"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2")
         .label("\u7535\u5b50\u5bfc\u7535\u76f8\u5185\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"unisel4", "unisel8"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("exterior", false);
    model.component("comp1").geom("geom1").feature("adjsel2").set("interior", true);

    model.param().set("n_hc", "0.5");
    model.param().descr("n_hc", "\u6c34\u5e73\u7535\u6c60\u6570");

    model.component("comp1").geom("geom1").run("adjsel2");

    model.title(null);

    model.description("");

    model.label("nonisothermal_pem_fuel_cell_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
