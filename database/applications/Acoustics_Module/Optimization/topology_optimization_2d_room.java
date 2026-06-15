/*
 * topology_optimization_2d_room.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class topology_optimization_2d_room {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.param().set("f0", "34.5[Hz]");
    model.param().descr("f0", "\u4f18\u5316\u7684\u9891\u7387");
    model.param().set("W", "18[m]");
    model.param().descr("W", "\u623f\u95f4\u5bbd\u5ea6");
    model.param().set("H", "9[m]");
    model.param().descr("H", "\u623f\u95f4\u9ad8\u5ea6");
    model.param().set("dH", "1[m]");
    model.param().descr("dH", "\u8bbe\u8ba1\u57df\u9ad8\u5ea6");
    model.param().set("Rob", "1[m]");
    model.param().descr("Rob", "\u76ee\u6807\u57df\u534a\u5f84");
    model.param().set("xob", "16[m]");
    model.param().descr("xob", "\u76ee\u6807\u57df x \u5750\u6807");
    model.param().set("yob", "2[m]");
    model.param().descr("yob", "\u76ee\u6807\u57df y \u5750\u6807");
    model.param().set("rho1", "1.204[kg/m^3]");
    model.param().descr("rho1", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("K1", "141.921e3[Pa]");
    model.param().descr("K1", "\u7a7a\u6c14\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param().set("rho2", "2643.0[kg/m^3]");
    model.param().descr("rho2", "\u94dd\u5bc6\u5ea6");
    model.param().set("K2", "68.7[GPa]");
    model.param().descr("K2", "\u94dd\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param().set("volfrac", "0.85");
    model.param().descr("volfrac", "\u8bbe\u8ba1\u57df\u4f53\u79ef\u5206\u6570");
    model.param().set("alpha_K", "0.001");
    model.param().descr("alpha_K", "\u963b\u5c3c\u7cfb\u6570");
    model.param().set("hmax", "0.3");
    model.param().descr("hmax", "\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param().set("rmin", "0.1*hmax");
    model.param().descr("rmin", "\u6700\u5c0f\u5355\u5143\u5927\u5c0f");
    model.param().set("beta", "32");
    model.param().descr("beta", "\u6295\u5f71\u659c\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dH", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Rob");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"xob", "yob"});
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 2, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 2, 1);
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u8bbe\u8ba1\u57df");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "H-dH/2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("acpr").feature("fpam1").set("LinearElasticOption", "Krho");
    model.component("comp1").physics("acpr").feature("fpam1").set("K_eff_mat", "userdef");
    model.component("comp1").physics("acpr").feature("fpam1").set("K_eff", "K1*(1+alpha_K*i)");
    model.component("comp1").physics("acpr").feature("fpam1").set("rho_mat", "userdef");
    model.component("comp1").physics("acpr").feature("fpam1").set("rho", "rho1");
    model.component("comp1").physics("acpr").create("mls1", "FrequencyMonopoleLineSource", 0);
    model.component("comp1").physics("acpr").feature("mls1").selection().named("geom1_pt1_pnt");
    model.component("comp1").physics("acpr").feature("mls1").set("QsperLength", 0.02);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "hmax/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("geom1_pt1_pnt");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "0.1*hmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u76ee\u6807\u51fd\u6570");
    model.component("comp1").probe("dom1").set("probename", "obj");
    model.component("comp1").probe("dom1").selection().named("geom1_c1_dom");
    model.component("comp1").probe("dom1").set("expr", "0.5*realdot(p,p)/acpr.pref_SPL^2");

    model.study("std1").feature("freq").set("plist", "range(24,0.1,42)");
    model.study("std1").feature("freq").set("probesel", "none");
    model.study("std1").label("\u7814\u7a76 1 - \u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 181, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 181, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 106, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 106, 0);
    model.result("pg2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u7814\u7a76 1 - \u521d\u59cb\u8bbe\u8ba1");

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().named("geom1_boxsel1");
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("L_min", "hmax");
    model.component("comp1").common("dtopo1").set("millingActive", "Enabled");
    model.component("comp1").common("dtopo1").setIndex("mil_yVector", -1, 0);
    model.component("comp1").common("dtopo1").set("beta", "beta");
    model.component("comp1").common("dtopo1").set("interpolationType", "Linear_interp");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "1");
    model.component("comp1").common().create("ftopobm1", "MaterialTopologyBoundary");
    model.component("comp1").common("ftopobm1").selection().set(4);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u8bbe\u8ba1\u57df\u53d8\u91cf");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_boxsel1");
    model.component("comp1").variable("var1").set("rhod_inv", "1/(1/rho2+dtopo1.theta_p*(1/rho1-1/rho2))");
    model.component("comp1").variable("var1").descr("rhod_inv", "\u8bbe\u8ba1\u57df\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("Kd_inv", "1/(1/K2+dtopo1.theta_p*(1/K1-1/K2))");
    model.component("comp1").variable("var1").descr("Kd_inv", "\u8bbe\u8ba1\u57df\u4f53\u79ef\u6a21\u91cf");

    model.component("comp1").physics("acpr").create("fpam2", "FrequencyPressureAcousticsModel", 2);
    model.component("comp1").physics("acpr").feature("fpam2")
         .label("\u538b\u529b\u58f0\u5b66\uff08\u8bbe\u8ba1\u57df\uff09");
    model.component("comp1").physics("acpr").feature("fpam2").selection().named("geom1_boxsel1");
    model.component("comp1").physics("acpr").feature("fpam2").set("LinearElasticOption", "Krho");
    model.component("comp1").physics("acpr").feature("fpam2").set("K_eff_mat", "userdef");
    model.component("comp1").physics("acpr").feature("fpam2").set("K_eff", "Kd_inv*(1+alpha_K*i)");
    model.component("comp1").physics("acpr").feature("fpam2").set("rho_mat", "userdef");
    model.component("comp1").physics("acpr").feature("fpam2").set("rho", "rhod_inv");

    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"acpr/fpam2"});
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").create("topo", "TopologyOptimization");
    model.study("std2").feature("topo").set("mmamaxiter", 50);
    model.study("std2").feature("topo").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("topo").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.study("std2").feature("topo").setIndex("optobj", "log10(comp1.obj)", 0);
    model.study("std2").feature("topo").setIndex("descr", "\u76ee\u6807\u57df\u58f0\u538b\u7ea7", 0);
    model.study("std2").feature("topo").set("constraintExpression", new String[]{"comp1.dtopo1.theta_avg"});
    model.study("std2").feature("topo").setIndex("constraintLbound", "volfrac", 0);
    model.study("std2").feature("topo").setIndex("constraintUbound", "", 0);
    model.study("std2").feature("topo").set("probesel", "none");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b (acpr) 1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7 (acpr) 1");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg4");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u9608\u503c");

    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt1").set("data", "dset2");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("smooth", "none");
    model.result().dataset("filt1").set("useder", false);
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("rangecolormin", 0);
    model.result("pg5").feature("surf1").set("rangecolormax", 1);
    model.result("pg6").set("data", "filt1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg3").run();

    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").label("\u7814\u7a76 2 - \u62d3\u6251\u4f18\u5316");

    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg6").feature("surf1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg6").feature("surf1").set("coloring", "colortable");

    model.study("std2").feature("topo").set("plot", true);
    model.study("std2").feature("topo").set("plotgroup", "pg6");

    model.sol("sol2").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u4f18\u5316");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_dtopo1_theta_f"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u94e3\u524a");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_dtopo1_theta_m1"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss2").label("\u58f0\u5b66");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_p"});

    model.study("std2").label("\u7814\u7a76 2 - \u62d3\u6251\u4f18\u5316");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").feature("param").setIndex("pname", "beta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "4 8 16 32", 0);
    model.study("std2").feature("param").set("probesel", "none");
    model.study("std2").feature("param").set("reusesol", true);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result("pg3").run();

    model.study("std2").feature("topo").set("probewindow", "");
    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").set("probesel", "none");
    model.study("std3").feature("freq").setSolveFor("/common/dtopo1", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").feature("freq").set("plist", "range(24,0.1,42)");
    model.study("std3").label("\u7814\u7a76 3 - \u4f18\u5316\u540e\u7684\u9891\u8c31");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u54cd\u5e94\u6bd4\u8f83");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u76ee\u6807\u57df\u4e2d\u7684\u58f0\u538b\u7ea7");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"obj"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.result("pg7").feature("glob1").setIndex("expr", "10*log10(obj)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u521d\u59cb\u8bbe\u8ba1", 0);
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset5");
    model.result("pg7").feature("glob2").setIndex("descr", "\u62d3\u6251\u4f18\u5316", 0);
    model.result().dataset("filt1").set("expr", "if(isdefined(dtopo1.theta_m), dtopo1.theta_m, 1)");
    model.result().dataset("filt1").set("smooth", "material");
    model.result().dataset("filt1").set("useder", true);
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").run("imp1");

    model.component("comp2").physics().create("acpr2", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("acpr2").feature("fpam1").set("LinearElasticOption", "Krho");
    model.component("comp2").physics("acpr2").feature("fpam1").set("K_eff_mat", "userdef");
    model.component("comp2").physics("acpr2").feature("fpam1").set("K_eff", "K1*(1+alpha_K*i)");
    model.component("comp2").physics("acpr2").feature("fpam1").set("rho_mat", "userdef");
    model.component("comp2").physics("acpr2").feature("fpam1").set("rho", "rho1");
    model.component("comp2").physics("acpr2").create("mls1", "FrequencyMonopoleLineSource", 0);
    model.component("comp2").physics("acpr2").feature("mls1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_pt1_pnt");
    model.component("comp2").physics("acpr2").feature("mls1").set("QsperLength", 0.02);

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().geom("geom2", 0);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_pt1_pnt");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "0.1*hmax");
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "hmax");
    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").probe().create("dom2", "Domain");
    model.component("comp2").probe("dom2").set("intsurface", true);
    model.component("comp2").probe("dom2").set("intvolume", true);
    model.component("comp2").probe("dom2").label("\u76ee\u6807\u51fd\u6570");
    model.component("comp2").probe("dom2").set("probename", "obj");
    model.component("comp2").probe("dom2").selection().named("geom2_imp1_mpart1_imp1_geom1_c1_dom");
    model.component("comp2").probe("dom2").set("expr", "0.5*realdot(p2,p2)/acpr2.pref_SPL^2");

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std4").feature("freq").set("probesel", "none");
    model.study("std4").feature("freq").setSolveFor("/common/dtopo1", false);
    model.study("std4").feature("freq").set("plist", "range(24,0.1,42)");
    model.study("std4").label("\u7814\u7a76 4 - \u9a8c\u8bc1");
    model.study("std4").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset7");
    model.result("pg8").setIndex("looplevel", 181, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg8").feature("surf1").set("colortable", "Wave");
    model.result("pg8").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b (acpr2)");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset7");
    model.result("pg9").setIndex("looplevel", 181, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"acpr2.Lp_t"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u58f0\u538b\u7ea7 (acpr2)");

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter("plotgroup", "pg9");
    model.nodeGroup().move("grp3", 3);

    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 1");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u9608\u503c 1");

    model.nodeGroup("grp3").add("plotgroup", "pg10");
    model.nodeGroup("grp3").add("plotgroup", "pg11");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668 1");
    model.result().dataset("filt2").set("data", "dset6");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg10").set("data", "dset6");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("colortabletrans", "none");
    model.result("pg10").feature("surf1").set("rangecolormin", 0);
    model.result("pg10").feature("surf1").set("rangecolormax", 1);
    model.result("pg11").set("data", "filt2");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "1");
    model.result("pg11").feature("surf1").set("coloring", "uniform");
    model.result("pg11").feature("surf1").set("color", "gray");
    model.result("pg11").feature("surf1").set("titletype", "none");
    model.result("pg8").run();

    model.nodeGroup().remove("grp3");

    model.result("pg8").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");
    model.nodeGroup("grp3").label("\u7814\u7a76 4 - \u9a8c\u8bc1");

    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 106, 0);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 106, 0);
    model.result("pg9").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("glob3", "glob2");
    model.result("pg7").run();
    model.result("pg7").feature("glob3").set("data", "dset7");
    model.result("pg7").feature("glob3").setIndex("descr", "\u786c\u58f0\u573a\u8fb9\u754c", 0);
    model.result("pg7").feature().duplicate("glob4", "glob3");
    model.result("pg7").run();
    model.result("pg7").feature("glob4").setIndex("descr", "\u4f18\u5316\u9891\u7387", 0);
    model.result("pg7").feature("glob4").set("xdata", "expr");
    model.result("pg7").feature("glob4").set("xdataexpr", "f0");
    model.result("pg7").feature("glob4").set("linecolor", "black");
    model.result("pg7").run();

    model
         .title("\u623f\u95f4\u4e8c\u7ef4\u6a21\u578b\u4e2d\u58f0\u5b66\u6a21\u6001\u7684\u62d3\u6251\u4f18\u5316\u4e0e\u9a8c\u8bc1");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u62d3\u6251\u4f18\u5316\u5728\u58f0\u5b66\u4e2d\u7684\u5e94\u7528\u3002\u4f18\u5316\u7684\u76ee\u6807\u662f\u5728\u7ed9\u5b9a\u7684\u8bbe\u8ba1\u57df\uff08\u8fd9\u91cc\u662f\u4e8c\u7ef4\u623f\u95f4\u7684\u5929\u82b1\u677f\uff09\u4e2d\u786e\u5b9a\u6700\u4f73\u6750\u6599\u5206\u5e03\uff08\u56fa\u4f53\u6216\u6c14\u4f53\uff09\uff0c\u4ece\u800c\u4f7f\u76ee\u6807\u533a\u57df\u7684\u5e73\u5747\u58f0\u538b\u7ea7\u6700\u5c0f\u5316\u3002\u672c\u4f8b\u9488\u5bf9\u5355\u4e00\u9891\u7387\u8fdb\u884c\u4f18\u5316\uff0c\u5c06\u62d3\u6251\u4f18\u5316\u7684\u8bbe\u8ba1\u8fdb\u4e00\u6b65\u8f6c\u6362\u4e3a\u51e0\u4f55\u5f62\u72b6\uff0c\u5e76\u4f7f\u7528\u786c\u58f0\u573a\u8fb9\u754c\u9a8c\u8bc1\u4f18\u5316\u7ed3\u679c\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("topology_optimization_2d_room.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
