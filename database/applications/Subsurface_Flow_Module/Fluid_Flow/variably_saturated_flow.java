/*
 * variably_saturated_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:30 by COMSOL 6.3.0.290. */
public class variably_saturated_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowRichards", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 2);
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.1);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{-0.5, 0});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.5, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6746");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(9);
    model.component("comp1").selection("sel1").set("groupcontang", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho", "1000[kg/m^3]", "\u6c34\u5bc6\u5ea6");
    model.param().set("poro", "0.43", "\u5b54\u9699\u7387");
    model.param().set("theta_r", "0.045", "\u6b8b\u4f59\u6db2\u4f53\u4f53\u79ef\u5206\u6570");
    model.param().set("K", "8.25e-5[m/s]", "\u6c34\u529b\u4f20\u5bfc\u7387");
    model.param().set("chi_f", "4.4e-10[1/Pa]", "\u6d41\u4f53\u7684\u53ef\u538b\u7f29\u6027");
    model.param().set("chi_p", "1e-8[1/Pa]", "\u56fa\u4f53\u7684\u53ef\u538b\u7f29\u6027");
    model.param().set("alpha", "14.5[1/m]", "\u672c\u6784\u5173\u7cfb\u5e38\u6570");
    model.param().set("n", "2.68", "\u672c\u6784\u5173\u7cfb\u5e38\u6570");
    model.param().set("l", "0.5", "\u672c\u6784\u5173\u7cfb\u5e38\u6570");
    model.param().set("Hp0", "-0.06[m]", "\u538b\u529b\u6c34\u5934");
    model.param("default").paramCase().create("case1");
    model.param("default").paramCase("case1").label("\u6848\u4f8b 1\uff1avan Genuchten");
    model.param("default").paramCase().create("case2");
    model.param("default").paramCase("case2").label("\u6848\u4f8b 2\uff1aBrooks-Corey");
    model.param("default").paramCase("case2").set("poro", "0.417");
    model.param("default").paramCase("case2").set("theta_r", "0.02");
    model.param("default").paramCase("case2").set("K", "5.8333e-5[m/s]");
    model.param("default").paramCase("case2").set("alpha", "13.8[1/m]");
    model.param("default").paramCase("case2").set("n", "0.592");
    model.param("default").paramCase("case2").set("l", "1");
    model.param("default").paramCase("case2").set("Hp0", "-0.2[m]");

    model.component("comp1").physics("dl").feature("usporous1").feature("fluid1")
         .set("fluidType", "compressibleLinearized");
    model.component("comp1").physics("dl").feature("usporous1").feature("fluid1").set("rhoref_mat", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").feature("fluid1").set("rhoref", "rho");
    model.component("comp1").physics("dl").feature("usporous1").feature("fluid1").set("chif_mat", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").feature("fluid1").set("chif", "chi_f");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("epsilon", "poro");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("chip", "chi_p");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("Ks", new String[]{"K", "0", "0", "0", "K", "0", "0", "0", "K"});
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("alpha", "alpha");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("l", "l");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("n", "n");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("theta_r", "theta_r");
    model.component("comp1").physics("dl").feature("usporous1")
         .label("\u975e\u9971\u548c\u591a\u5b54\u4ecb\u8d28 (van Genuchten)");
    model.component("comp1").physics("dl").feature().duplicate("usporous2", "usporous1");
    model.component("comp1").physics("dl").feature("usporous2")
         .label("\u975e\u9971\u548c\u591a\u5b54\u4ecb\u8d28 (Brooks-Corey)");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1")
         .set("retentionModel", "BrooksAndCorey");
    model.component("comp1").physics("dl").feature("init1").set("InitType", "Hp");
    model.component("comp1").physics("dl").feature("init1").set("Hp", "Hp0");
    model.component("comp1").physics("dl").create("ph1", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph1").selection().set(2, 3);
    model.component("comp1").physics("dl").feature("ph1").set("Hp0", "Hp0");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection()
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.025);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "0 1 5 10 15");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"dl/usporous2"});
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std1").feature("param").setIndex("switchcase", "user", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", 1, 0);
    model.study("std1").label("\u7814\u7a76 1\uff1avan Genuchten");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u4e00\u7c7b\u571f");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "dl.Se");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "dl.Hp");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("color", "black");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/dl", true);
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "0 1 5 10 15");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").set("sweeptype", "switch");
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std2").feature("param").setIndex("switchcase", "user", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", 2, 0);
    model.study("std2").label("\u7814\u7a76 2\uff1aBrooks-Corey");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol5");
    model.batch("p2").run("compute");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4e8c\u7c7b\u571f");
    model.result("pg2").set("data", "dset3");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6709\u6548\u9971\u548c\u5ea6");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().named("sel1");
    model.result("pg3").feature("lngr1").set("expr", "dl.Se");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "atan2(y,x)");
    model.result("pg3").feature("lngr1").set("xdataunit", "\u00b0");
    model.result("pg3").feature("lngr1").create("filt1", "LineGraphFilter");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").feature("filt1").set("expr", "abs(atan2(y,x))<pi");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("data", "dset3");
    model.result("pg3").feature("lngr2").set("linestyle", "dashed");
    model.result("pg3").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6709\u6548\u9971\u548c\u5ea6\uff0cS<sub>e</sub> (1)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().set(1);
    model.result().numerical("av1").setIndex("expr", "dl.Se", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical("av1").set("data", "dset3");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").appendResult();
    model.result().numerical().create("av2", "AvLine");
    model.result().numerical("av2").set("intsurface", true);
    model.result().numerical("av2").selection().named("sel1");
    model.result().numerical("av2").setIndex("expr", "dl.Se", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u5e73\u5747\u503c 2");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").setResult();
    model.result().numerical("av2").set("data", "dset3");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").appendResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl2");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1")
         .setIndex("legends", "\u6746\u4e0a\u7684 S<sub>e</sub>\uff0c\u4e00\u7c7b\u571f", 0);
    model.result("pg4").feature("tblp1")
         .setIndex("legends", "\u6746\u4e0a\u7684 S<sub>e</sub>\uff0c\u4e8c\u7c7b\u571f", 1);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("table", "tbl1");
    model.result("pg4").feature("tblp2").set("linestyle", "dashed");
    model.result("pg4").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg4").feature("tblp2")
         .setIndex("legends", "\u603b\u4f53 S<sub>e</sub>\uff0c\u4e00\u7c7b\u571f", 0);
    model.result("pg4").feature("tblp2")
         .setIndex("legends", "\u603b\u4f53 S<sub>e</sub>\uff0c\u4e8c\u7c7b\u571f", 1);
    model.result("pg4").run();
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("data", "dset1");
    model.result("pg4").feature("lngr1").selection().named("sel1");
    model.result("pg4").feature("lngr1").set("expr", "dl.Se");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "t");
    model.result("pg4").feature("lngr1").set("xdataunit", "min");
    model.result("pg4").feature("lngr1").set("linecolor", "blue");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature("lngr1").set("linemarker", "circle");
    model.result("pg4").feature("lngr1").set("markerpos", "interp");
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("data", "dset3");
    model.result("pg4").feature("lngr2").set("linecolor", "green");
    model.result("pg4").feature("lngr2").set("linemarker", "square");
    model.result("pg4").run();
    model.result("pg4").label("\u5e73\u5747\u6709\u6548\u9971\u548c\u5ea6");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5e73\u5747\u6709\u6548\u9971\u548c\u5ea6\uff0cS<sub>e</sub>");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "S<sub>e</sub>(1)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset2");
    model.result().dataset("extr1").set("planemap", "xz");
    model.result().dataset().duplicate("extr2", "extr1");
    model.result().dataset("extr2").set("data", "dset4");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e09\u7ef4\u7ed8\u56fe\uff1a\u4e00\u7c7b\u571f");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("expr", "dl.Se");
    model.result("pg5").feature("slc1").set("descr", "\u6709\u6548\u9971\u548c\u5ea6");
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").feature("slc1").set("quickynumber", 1);
    model.result("pg5").feature("slc1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "custom");
    model.result("pg5")
         .set("suffixintitle", "\u6709\u6548\u9971\u548c\u5ea6\uff08\u5207\u9762\uff09\u548c\u6d41\u901f\uff08\u6d41\u7ebf\uff09");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("slc2", "slc1");
    model.result("pg5").run();
    model.result("pg5").feature("slc2").set("quickplane", "xy");
    model.result("pg5").feature("slc2").set("quickznumber", 1);
    model.result("pg5").feature("slc2").set("interactive", true);
    model.result("pg5").feature("slc2").set("shift", -0.1);
    model.result("pg5").feature("slc2").set("inheritplot", "slc1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"dl.u", "0", "dl.v"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("startmethod", "coord");
    model.result("pg5").feature("str1").set("xcoord", "range(-1,0.1,1)");
    model.result("pg5").feature("str1").set("ycoord", -0.5);
    model.result("pg5").feature("str1").set("zcoord", 1);
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u4e09\u7ef4\u7ed8\u56fe\uff1a\u4e8c\u7c7b\u571f");
    model.result("pg6").set("data", "extr2");
    model.result("pg6").run();

    model.title("\u53d8\u9971\u548c\u6d41\u52a8");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u201c\u7406\u67e5\u5179\u65b9\u7a0b\u201d\u6a21\u578b\uff0c\u6bd4\u8f83\u4e86\u4e24\u4e2a\u571f\u5757\u4e2d\u4e0d\u6e17\u900f\u7279\u6027\u5468\u56f4\u7684\u53d8\u9971\u548c\u6d41\u3002\u57fa\u4e8e\u6c34\u6587\u7814\u7a76\u8005\u7684\u8d21\u732e\uff0c\u672c\u4f8b\u6f14\u793a\u4e86 van Genuchten \u548c Brooks-Corey \u4fdd\u6301\u548c\u6e17\u900f\u7387\u51fd\u6570\u7684\u81ea\u52a8\u6c42\u89e3\u8fc7\u7a0b\u3002");

    model.label("variably_saturated_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
