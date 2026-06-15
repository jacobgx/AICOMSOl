/*
 * microconnector_bump_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:00 by COMSOL 6.3.0.290. */
public class microconnector_bump_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "100[um]", "\u7535\u6781\u957f\u5ea6");
    model.param().set("L2", "50[um]", "\u51fa\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("L3", "30[um]", "\u5165\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("Ltot", "L1+L2+L3", "\u7535\u6c60\u603b\u957f\u5ea6");
    model.param().set("h1", "10[um]", "\u5149\u523b\u80f6\u819c\u539a\u5ea6");
    model.param().set("h2", "40[um]", "\u6d41\u4f53\u5165\u53e3/\u51fa\u53e3\u539a\u5ea6");
    model.param().set("rho", "1.1e3[kg/m^3]", "\u6db2\u4f53\u5bc6\u5ea6");
    model.param().set("nu", "1.394e-6[m^2/s]", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("mu", "nu*rho", "\u52a8\u529b\u9ecf\u5ea6");
    model.param()
         .set("h_Pe", "10[um]", "\u4e0e\u901f\u5ea6\u8868\u9762\u7684\u8ddd\u79bb\uff08\u7528\u4f69\u514b\u83b1\u7279\u6570\u8868\u793a\uff09");
    model.param().set("Pe", "41.6", "\u4f69\u514b\u83b1\u7279\u6570");
    model.param().set("D", "4.5e-10[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_bulk", "0.6[mol/dm^3]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("u_bulk", "h2/h_Pe*Pe*D/(h1)", "\u672c\u4f53\u901f\u5ea6");
    model.param().set("r_edge", "h1/4", "\u8fb9\u534a\u5f84\uff08\u4e8c\u7ef4\u4e2d\u672a\u4f7f\u7528\uff09");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L1+L2+L3", "h2"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-L3", "h1"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L1", "h1"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("u_profile", "u_bulk*(y-h1)/h2", "\u5165\u53e3/\u51fa\u53e3\u901f\u5ea6\u66f2\u7ebf");
    model.component("comp1").variable("var1")
         .set("i_loc", "-tds.ntflux_c*F_const*2", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("i_loc_norm", "-i_loc/maxop1(-i_loc)", "\u5f52\u4e00\u5316\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().set(5);

    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl1").set("u0", new String[]{"u_profile", "0", "0"});
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc2").set("utr", new String[]{"u_bulk", "0", "0"});
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(8);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").label("\u6d53\u5ea6 - \u672c\u4f53");
    model.component("comp1").physics("tds").feature("conc1").selection().set(1, 3);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tds").create("conc2", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc2").label("\u6d53\u5ea6 - \u7535\u6781\u8868\u9762");
    model.component("comp1").physics("tds").feature("conc2").selection().set(5);
    model.component("comp1").physics("tds").feature("conc2").setIndex("species", true, 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(8);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_bulk", 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Pe", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1.31 41.6", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("colortable", "Rainbow");
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6\uff0c\u6d41\u7ebf");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.01);
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg4").feature("str1").feature("col1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u4e0d\u540c\u4f69\u514b\u83b1\u7279\u6570\u5bf9\u5e94\u7684\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(5);
    model.result("pg5").feature("lngr1").set("expr", "i_loc");
    model.result("pg5").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f52\u4e00\u5316\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u4e0d\u540c\u4f69\u514b\u83b1\u7279\u6570\u5bf9\u5e94\u7684\u5f52\u4e00\u5316\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(5);
    model.result("pg6").feature("lngr1").set("expr", "i_loc_norm");
    model.result("pg6").feature("lngr1").set("descr", "\u5f52\u4e00\u5316\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").run();
    model.result("pg1").run();

    model.title("\u5fae\u578b\u63a5\u5934\u51f8\u5757\u7535\u9540 - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5fae\u578b\u63a5\u5934\u51f8\u5757\u7684\u6c89\u79ef\u3002\u6c89\u79ef\u8fc7\u7a0b\u53d7\u8d28\u91cf\u4f20\u9012\u9650\u5236\uff0c\u7814\u7a76\u4e86\u5404\u79cd\u6d41\u4f53\u901f\u5ea6\u5bf9\u7535\u6781\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\u7684\u5f71\u54cd\u3002");

    model.label("microconnector_bump_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
