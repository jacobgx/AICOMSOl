/*
 * squeeze_film_perforated_plates.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:56 by COMSOL 6.3.0.290. */
public class squeeze_film_perforated_plates {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tff", "ThinFilmFlowDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/tff", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("M_h", "36", "\u6cbf\u957f\u5ea6\u65b9\u5411\u7684\u5b54\u6570");
    model.param().set("N_h", "6", "\u6cbf\u5bbd\u5ea6\u65b9\u5411\u7684\u5b54\u6570");
    model.param().set("h0", "1.6[um]", "\u95f4\u9699\u9ad8\u5ea6");
    model.param().set("l_h", "15[um]", "\u5b54\u957f\u5ea6\uff08\u677f\u539a\u5ea6\uff09");
    model.param().set("l_pl", "370[um]", "\u677f\u957f");
    model.param().set("w_pl", "65[um]", "\u677f\u5bbd");
    model.param().set("s_h", "6[um]", "\u65b9\u5b54\u8fb9\u957f");
    model.param().set("s_1", "w_pl/(N_h+1)-s_h", "\u5b54\u95f4\u8ddd");
    model.param().set("s_2", "s_1+s_h/2", "\u5b54\u4e0e\u677f\u8fb9\u7f18\u7684\u8ddd\u79bb");
    model.param().set("s1p", "l_pl/(M_h+1)-s_h");
    model.param().set("s2p", "s1p+s_h/2");
    model.param().set("pitch__", "s_h+s_1");
    model.param().set("l_per", "l_pl-2*(s2p-s1p)", "\u7a7f\u5b54\u533a\u57df\u957f\u5ea6");
    model.param().set("w_per", "w_pl-2*(s_2-s_1)", "\u7a7f\u5b54\u533a\u57df\u5bbd\u5ea6");
    model.param().set("r0", "sqrt(l_per*w_per/M_h/N_h/pi)", "\u5355\u4f4d\u5706\u76d8\u534a\u5f84");
    model.param().set("ri", "s_h/sqrt(pi)", "\u5b54\u7684\u7b49\u6548\u534a\u5f84");
    model.param().set("dhND", "0.05", "\u95f4\u9699\u9ad8\u5ea6\u53d8\u5316\u7387");
    model.param().set("dh", "dhND*h0", "\u95f4\u9699\u9ad8\u5ea6\u53d8\u5316");
    model.param().set("mu0", "1.8e-5[Pa*s]", "\u6c14\u4f53\u9ecf\u5ea6");
    model.param().set("f0", "10000[Hz]", "\u632f\u52a8\u9891\u7387");
    model.param().set("vf", "2*pi*f0*dh", "\u58c1\u901f\u5ea6");
    model.param().set("pRef", "1[atm]");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"l_pl", "w_pl"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"l_per", "w_per"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"s2p-s1p", "s_2-s_1"});

    model.material().create("mat1", "Common", "");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").physics("tff").label("\u8584\u819c\u6d41\uff0c\u57df - \u65e0\u7a7f\u5b54");
    model.component("comp1").physics("tff").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp1").physics("tff").prop("ReferencePressure").set("pref", "pRef");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "h0");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialWallVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vw", new String[]{"0", "0", "vf"});
    model.component("comp1").physics().create("tff2", "ThinFilmFlowDomain", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/tff2", true);

    model.component("comp1").physics("tff2").label("\u8584\u819c\u6d41\uff0c\u57df - Bao \u6a21\u578b");
    model.component("comp1").physics("tff2").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp1").physics("tff2").prop("ReferencePressure").set("pref", "pRef");
    model.component("comp1").physics("tff2").feature("ffp1").set("hw1", "h0");
    model.component("comp1").physics("tff2").feature("ffp1").set("TangentialWallVelocity", "userdef");
    model.component("comp1").physics("tff2").feature("ffp1").set("vw", new String[]{"0", "0", "vf"});
    model.component("comp1").physics("tff2").create("perf1", "Perforations", 2);
    model.component("comp1").physics("tff2").feature("perf1").selection().set(2);
    model.component("comp1").physics("tff2").feature("perf1").set("PerforationAdmittance", "Bao_model");
    model.component("comp1").physics("tff2").feature("perf1").set("s_h", "s_h");
    model.component("comp1").physics("tff2").feature("perf1").set("l_h", "l_h");
    model.component("comp1").physics("tff2").feature("perf1").set("n_h", "1/(l_per*w_per/M_h/N_h)");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("f_tot_no_perf", "intop1(tff.fwallz)");
    model.component("comp1").variable("var1")
         .descr("f_tot_no_perf", "\u4e0d\u5e26\u7a7f\u5b54\u60c5\u51b5\u4e0b\u7684\u603b\u529b");
    model.component("comp1").variable("var1").set("f_tot_Bao", "intop1(tff2.fwallz)");
    model.component("comp1").variable("var1")
         .descr("f_tot_Bao", "\u5e26\u7a7f\u5b54\u60c5\u51b5\u4e0b\u7684\u603b\u529b\uff1bBao \u6a21\u578b");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u5b9e\u9a8c\u6570\u636e\u63d2\u503c");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "squeeze_film_perforated_plates_exp_data.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("interp", "neighbor");
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", "uN*s/m", 0);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("\u00b5m");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"l_pl", "w_pl"});
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("sq1", "Square");
    model.component("comp2").geom("geom2").feature("sq1").set("size", "s_h");
    model.component("comp2").geom("geom2").feature("sq1").set("pos", new String[]{"s2p", "s_2"});
    model.component("comp2").geom("geom2").run("sq1");
    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("sq1");
    model.component("comp2").geom("geom2").feature("arr1").set("fullsize", new String[]{"M_h", "N_h"});
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new String[]{"s_h+s1p", "s_h+s_1"});
    model.component("comp2").geom("geom2").feature("arr1").set("selresult", true);
    model.component("comp2").geom("geom2").run("arr1");
    model.component("comp2").geom("geom2").create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("dif1").selection("input").set("r1");
    model.component("comp2").geom("geom2").feature("dif1").selection("input2").named("arr1");
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics().create("tff3", "ThinFilmFlowDomain", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/tff3", true);

    model.component("comp2").physics("tff3")
         .label("\u8584\u819c\u6d41\uff0c\u57df - \u7a7f\u5b54\u5904\u7684 P = 0");
    model.component("comp2").physics("tff3").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp2").physics("tff3").prop("ReferencePressure").set("pref", "pRef");
    model.component("comp2").physics("tff3").feature("ffp1").set("hw1", "h0");
    model.component("comp2").physics("tff3").feature("ffp1").set("TangentialWallVelocity", "userdef");
    model.component("comp2").physics("tff3").feature("ffp1").set("vw", new String[]{"0", "0", "vf"});

    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().all();

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("f_tot_p0", "intop2(tff3.fwallz)");
    model.component("comp2").variable("var2")
         .descr("f_tot_p0", "\u7a7f\u5b54\u5904\u76f8\u5bf9\u538b\u529b\u4e3a\u96f6\u60c5\u51b5\u4e0b\u7684\u603b\u529b");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param")
         .set("plistarr", new String[]{"3 3 3 3 3 3 3 3 3 3 3 3 1.6 1.6 1.6 1.6 1.6 1.6", "55 55 115 115 185 185 376 376 376 376 376 376 370 370 370 370 370 370", "55 55 115 115 185 185 99 99 99 99 158 277 65 65 65 65 120 240", "7.2 12.6 7.2 12.6 7.2 12.6 7.2 9.3 10.7 12.6 7.2 7.2 5 6 7 8 6 6", "2 2 5 5 8 8 18 18 18 18 18 18 36 36 36 36 36 36", "2 2 5 5 8 8 4 4 4 4 7 13 6 6 6 6 12 24", "71 79 41 46 22 28 20 21 21 22 17 14 200 200 210 220 170 140", "6.3 6.3 6.3 6.3 6.3 6.3 6.3 6.3 6.3 6.3 6.3 6.3 15 15 15 15 15 15"});
    model.study("std1").feature("param")
         .set("pname", new String[]{"h0", "l_pl", "w_pl", "s_h", "M_h", "N_h", "f0", "l_h"});
    model.study("std1").feature("param").set("punit", new String[]{"um", "um", "um", "um", "", "", "kHz", "um"});
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (tff)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 18, 1);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (tff2)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 18, 1);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "pfilm2");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6d41\u4f53\u538b\u529b (tff3)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 18, 1);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4 1 - \u65e0\u7a7f\u5b54");
    model.result("pg2").run();
    model.result("pg2").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4 2 - Bao \u6a21\u578b");
    model.result("pg3").run();
    model.result("pg3").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4 3 - \u7a7f\u5b54\u5904\u7684 P=0");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u963b\u5c3c\u7cfb\u6570");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u963b\u5c3c\u7cfb\u6570 (N*s/m)\uff1a\u4e0e\u5b9e\u9a8c\u6570\u636e\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u963b\u5c3c\u7cfb\u6570");
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(f_tot_no_perf)/vf", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u65e0\u7a7f\u5b54", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(f_tot_Bao)/vf", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "Bao \u6a21\u578b", 1);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset4");
    model.result("pg4").feature("glob2").set("expr", new String[]{});
    model.result("pg4").feature("glob2").set("descr", new String[]{});
    model.result("pg4").feature("glob2").setIndex("expr", "abs(f_tot_p0)/vf", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u7a7f\u5b54\u5904\u7684 P=0", 0);

    model.component("comp1").func("int1").createPlot("pg5");

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("plot1").set("data", "int1_ds1");
    model.result("pg4").run();
    model.result("pg4").feature().copy("plot1", "pg5/plot1");
    model.result("pg4").run();
    model.result("pg4").feature("plot1").set("unit", "N*s/m");
    model.result("pg4").feature("plot1").set("display", "points");
    model.result("pg4").feature("plot1").set("linewidth", 6);
    model.result("pg4").run();

    model.title("\u7a7f\u5b54\u677f\u538b\u819c\u963b\u5c3c");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u7a7f\u5b54\u677f\u963b\u5c3c\u7cfb\u6570\u4e0e\u5b9e\u9a8c\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002\u8be5\u4eff\u771f\u5305\u542b 18 \u79cd\u4e0d\u540c\u7684\u51e0\u4f55\u6784\u578b\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u8584\u819c\u6d41\u201d\u7269\u7406\u573a\u63a5\u53e3\u5185\u7f6e\u7684 Bao \u7a7f\u5b54\u6a21\u578b\uff0c\u8fd8\u6a21\u62df\u4e86\u4e24\u79cd\u6781\u9650\u60c5\u51b5\uff1a\u65e0\u7a7f\u5b54\u548c\u7a7f\u5b54\u76f8\u5bf9\u538b\u529b\u4e3a\u96f6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();

    model.label("squeeze_film_perforated_plates.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
