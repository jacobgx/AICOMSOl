/*
 * tesla_microvalve_transient_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:09 by COMSOL 6.3.0.290. */
public class tesla_microvalve_transient_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "1[mm]", "\u57df\u957f\u5ea6");
    model.param().set("H", "0.35*L", "\u57df\u9ad8\u5ea6");
    model.param().set("L1", "L/10", "\u5165\u53e3\u9ad8\u5ea6");
    model.param().set("Re", "500", "\u96f7\u8bfa\u6570");
    model.param().set("tmax", "1.75*Tperiod", "\u4eff\u771f\u65f6\u95f4");
    model.param().set("meshsz", "0.01*L", "\u7f51\u683c\u5927\u5c0f");
    model.param().set("alpha", "1e4*mu0/L1^2", "\u963b\u5c3c\u53c2\u6570");
    model.param().set("mu0", "1e-3[Pa*s]", "\u9ecf\u5ea6");
    model.param().set("rho0", "1e3[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("U0", "Re*mu0/L/rho0", "\u7279\u5f81\u901f\u5ea6");
    model.param().set("Tperiod", "L/U0*10", "\u5468\u671f\u957f\u5ea6");
    model.param().set("dp", "Re*mu0^2/L1^2/rho0", "\u538b\u964d\u5e45\u5ea6");
    model.param().set("beta", "8", "\u6295\u5f71\u659c\u7387");
    model.param().set("q", "0.01", "\u63d2\u503c\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L1");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new String[]{"-L1", "0"});
    model.component("comp1").geom("geom1").feature("sq1").set("selresult", true);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("sq1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "L+L1");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "-L1*0.999");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "L+L1*0.999");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5165\u53e3/\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel2", "boxsel3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").label("\u538b\u529b\u53c2\u8003\u70b9");
    model.component("comp1").geom("geom1").feature("disksel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("disksel1").set("r", "L/1000");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6c34");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().named("geom1_r1_dom");
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("L_min", "meshsz");
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("beta", "beta");
    model.component("comp1").common("dtopo1").set("interpolationType", "Darcy");
    model.component("comp1").common("dtopo1").set("q_Darcy", "q");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "1");
    model.component("comp1").common().create("ftopom1", "MaterialTopologyDomain");
    model.component("comp1").common("ftopom1").selection().named("geom1_sq1_dom");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u5e73\u5747\u901f\u5ea6");
    model.component("comp1").probe("dom1").set("probename", "uAvg");
    model.component("comp1").probe("dom1").selection().named("geom1_r1_dom");
    model.component("comp1").probe("dom1").set("type", "integral");
    model.component("comp1").probe("dom1").set("expr", "u/L/H");

    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 1);
    model.component("comp1").physics("spf").feature("pfc1").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").feature("pfc1").set("pdiff", "dp*cos(2*pi*t/Tperiod)");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().named("geom1_disksel1");
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("spf").feature("vf1")
         .set("F", new String[]{"-alpha*dtopo1.theta_p*u", "-alpha*dtopo1.theta_p*v", "0"});
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "obj", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "objt*tmax-(tmax-Tperiod < t)*uAvg/U0", 0, 0);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u4f18\u5316");
    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").set("mmamaxiter", 20);
    model.study("std1").feature("topo").set("movelimitactive", true);
    model.study("std1").feature("topo").set("movelimit", 0.2);
    model.study("std1").feature("topo").set("optobj", new String[]{"comp1.obj"});
    model.study("std1").feature("topo").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.study("std1").feature("topo").set("objectivetype", "maximization");
    model.study("std1").feature("topo").set("objectivescaling", "manual");
    model.study("std1").feature("topo").set("objscaleval", "1.5e-3");
    model.study("std1").feature("topo").set("probesel", "none");
    model.study("std1").feature("time").set("tlist", "range(0,tmax/7,tmax)");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"obj"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("expr", new String[]{"obj"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg3");
    model.nodeGroup().move("grp1", 1);

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u9608\u503c");

    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt1").set("data", "dset1");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("smooth", "none");
    model.result().dataset("filt1").set("useder", false);
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").set("rangecolormin", 0);
    model.result("pg4").feature("surf1").set("rangecolormax", 1);
    model.result("pg5").set("data", "filt1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.sol("sol1").feature("o1").set("stationaryhidesens", "off");
    model.sol("sol1").feature("o1").set("stationaryhideadj", "off");
    model.sol("sol1").feature("o1").set("gcmma", false);
    model.sol("sol1").feature("o1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("o1").feature("t1").create("se1", "Segregated");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ssDef").label("\u4f18\u5316");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_dtopo1_theta_f"});
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ss1").label("\u6d41\u4f53\u6d41\u52a8");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_p", "comp1_u"});
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ss2").label("\u76ee\u6807");
    model.sol("sol1").feature("o1").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_ODE1"});
    model.sol("sol1").feature("o1").feature("t1").feature("d1").set("linsolver", "pardiso");

    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg4");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.result("pg3").run();
    model.result("pg3").label("\u76ee\u6807");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").set("data", "filt1");
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result().dataset("filt1").set("expr", "dtopo1.theta_f");
    model.result().dataset("filt1").set("descr", "\u8fc7\u6ee4\u7684\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").feature("imp1").importData();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").run("imp1");

    model.component("comp1").label("\u7ec4\u4ef6 1\uff1a\u4f18\u5316");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").label("\u7ec4\u4ef6 2\uff1a\u9a8c\u8bc1");

    model.component("comp2").physics().copy("spf2", "spf");
    model.component("comp2").physics().copy("ge2", "ge");

    model.component("comp2").material().copy("mat2", "mat1");

    model.component("comp2").probe().copy("dom2", "dom1");
    model.component("comp2").probe("dom2").set("probename", "uAvg");
    model.component("comp2").probe("dom2").selection().named("geom2_imp1_mpart1_imp1_geom1_r1_dom");

    model.component("comp2").physics("spf2").feature("sym1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_boxsel1");
    model.component("comp2").physics("spf2").feature("pfc1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_unisel1");
    model.component("comp2").physics("spf2").feature("prpc1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_disksel1");
    model.component("comp2").physics("spf2").feature().remove("vf1");

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "meshsz/2");
    model.component("comp2").mesh("mesh2").feature("size").set("hgrad", Double.POSITIVE_INFINITY);
    model.component("comp2").mesh("mesh2").feature("size").set("hcurve", Double.POSITIVE_INFINITY);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/ge", false);
    model.study("std2").feature("time").setSolveFor("/common/dtopo1", false);
    model.study("std2").feature("time").set("probesel", "none");
    model.study("std1").feature("time").setSolveFor("/physics/spf2", false);
    model.study("std1").feature("time").setSolveFor("/physics/ge2", false);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().dataset("dset4").set("geom", "geom2");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u901f\u5ea6 (spf2)");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u538b\u529b (spf2)");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2").set("expr", new String[]{"obj"});
    model.result().numerical("gev2").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"obj"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316 1");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg8");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 1");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u9608\u503c 1");

    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668 1");
    model.result().dataset("filt2").set("data", "dset3");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg9").feature("surf1").set("rangecolormin", 0);
    model.result("pg9").feature("surf1").set("rangecolormax", 1);
    model.result("pg10").set("data", "filt2");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "1");
    model.result("pg10").feature("surf1").set("coloring", "uniform");
    model.result("pg10").feature("surf1").set("color", "gray");
    model.result("pg10").feature("surf1").set("titletype", "none");
    model.result("pg6").run();

    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").create("se1", "Segregated");
    model.sol("sol2").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("t1").feature("se1").feature("ssDef").label("\u6d41\u4f53\u6d41\u52a8");
    model.sol("sol2").feature("t1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp2_p", "comp2_u"});
    model.sol("sol2").feature("t1").feature("se1").feature("ss1").label("\u76ee\u6807");
    model.sol("sol2").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp2_ODE1"});

    model.study("std2").label("\u9a8c\u8bc1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg6").run();
    model.result("pg1").run();

    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup().remove("grp2");

    model.result("pg8").run();
    model.result("pg8").label("\u76ee\u6807\uff08\u9a8c\u8bc1\uff09");
    model.result("pg6").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").label("\u9a8c\u8bc1");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg6").run();
    model.result("pg6").stepLast(0);
    model.result("pg6").run();
    model.result("pg6").stepPrevious(0);
    model.result("pg6").run();

    model.title("\u5177\u6709\u77ac\u6001\u6d41\u52a8\u7684 Tesla \u5fae\u9600\u7684\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u5bf9\u4ea7\u751f\u632f\u8361\u538b\u964d\u7684 Tesla \u5fae\u9600\u8fdb\u884c\u62d3\u6251\u4f18\u5316\u3002Tesla \u5fae\u9600\u5229\u7528\u6469\u64e6\u529b\u800c\u4e0d\u662f\u8fd0\u52a8\u90e8\u4ef6\u6765\u6291\u5236\u56de\u6d41\uff0c\u56e0\u6b64\u5176\u76ee\u6807\u662f\u5b9e\u73b0\u5e73\u5747\u6d41\u7387\u7684\u6700\u5927\u5316\u3002\u6211\u4eec\u53ef\u4ee5\u901a\u8fc7\u5728\u5efa\u6a21\u57df\u5185\u5206\u914d\u6750\u6599\u6765\u4f18\u5316\u8bbe\u8ba1\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tesla_microvalve_transient_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
