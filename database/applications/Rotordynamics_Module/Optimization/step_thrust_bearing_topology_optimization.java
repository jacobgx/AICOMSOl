/*
 * step_thrust_bearing_topology_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:46 by COMSOL 6.3.0.290. */
public class step_thrust_bearing_topology_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ro", "0.1[m]", "Outer radius of the pad");
    model.param().set("Ri", "0.05[m]", "Inner radius of the pad");
    model.param().set("gAng", "15", "Groove angle (deg)");
    model.param().set("hg", "1e-4[m]", "Groove depth");
    model.param().set("hf", "1e-4[m]", "Film thickness");
    model.param().set("speed", "1000[rad/s]", "Angular speed of the shaft");
    model.param().set("mu_f", "0.072[Pa*s]", "Viscosity of the lubricant");
    model.param().set("rho_c", "866[kg/m^3]", "Density at cavitation pressure");
    model.param().set("N", "3.0", "Number of pads");
    model.param().set("initUniform", "1.0", "Uniform initialization");
    model.param().set("volfrac", "0", "Volume fraction");
    model.param().set("meshsz", "2[mm]", "Mesh size");
    model.param().set("beta", "1", "Projection slope");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Ro");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "Ri");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("dif1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("dif1", 8);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("tool").set("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("par1");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"wp1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").set("interior", true);
    model.component("comp1").geom("geom1").feature("adjsel1").set("exterior", false);
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u5185\u90e8\u8fb9");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u5706\u5468\u8fb9");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");

    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");

    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().geom("geom1", 2);
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("beta", "beta");
    model.component("comp1").common("dtopo1").set("interpolationType", "Linear_interp");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "if(initUniform,volfrac,0.5+0.5*sin(N*atan2(Yg,Xg)))");

    model.component("comp1").physics("hdb").create("htb1", "HydrodynamicThrustBearing", 2);
    model.component("comp1").physics("hdb").feature("htb1").selection().all();
    model.component("comp1").physics("hdb").feature("htb1").set("RefNormal", "InverseOrientation");
    model.component("comp1").physics("hdb").feature("htb1").set("BearingType", "UserDef");
    model.component("comp1").physics("hdb").feature("htb1").set("hB1", "hg+hf*dtopo1.theta_p");
    model.component("comp1").physics("hdb").feature("htb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("htb1").set("Ow", "speed");
    model.component("comp1").physics("hdb").feature("htb1").set("rho_c", "rho_c");
    model.component("comp1").physics("hdb").feature("bax1").set("bearingAxis", "zAxis");
    model.component("comp1").physics("hdb").feature("bax1").set("e_aux", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").label("\u7814\u7a76\uff1a\u626b\u63cf\u521d\u59cb\u6761\u4ef6");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Ro", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Ro", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Ri", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "Ri", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "N", 0);
    model.study("std1").feature("param").setIndex("plistarr", "3 3 4 5", 0);
    model.study("std1").feature("param").setIndex("pname", "initUniform", 1);
    model.study("std1").feature("param").setIndex("plistarr", "1 0 0 0", 1);
    model.study("std1").feature("topo").set("optobj", new String[]{"comp1.hdb.htb1.Fcz"});
    model.study("std1").feature("topo")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.study("std1").feature("topo").set("objectivescaling", "init");
    model.study("std1").feature("topo").set("objectivetype", "maximization");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").getInitialValue();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("inherittubescale", false);
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("grp1", 1);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u9608\u503c");

    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt1").set("data", "dset2");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("smooth", "none");
    model.result().dataset("filt1").set("useder", false);
    model.result().dataset("filt1").set("level", "surface");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("rangecolormin", 0);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2);
    model.result("pg3").set("data", "filt1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset1");

    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg2");

    model.sol("sol1").feature("o1").feature("s1").feature("se1").feature().move("ss2", 0);

    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").run();
    model.result("pg2").set("data", "dset1");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u627f\u8f7d\u529b vs. \u8f74\u74e6\u6570\u91cf");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{2, 3, 4}, 0);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"hdb.htb1.Fcz"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "N");
    model.result("pg4").feature("glob1").set("linewidth", 3);
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result().dataset("filt1").feature().create("mip1", "MeshImportParameters");
    model.result().dataset("filt1").feature("mip1").setIndex("looplevel", 3, 0);
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart1").feature("imp1").importData();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "geom1");
    model.component("comp2").geom("geom2").feature().duplicate("imp2", "imp1");
    model.component("comp2").geom("geom2").feature("imp2").label("\u8f74\u627f\u69fd");
    model.component("comp2").geom("geom2").feature("imp2").set("type", "mesh");
    model.component("comp2").geom("geom2").feature("imp2").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").feature("imp2").set("simplifymesh", false);
    model.component("comp2").geom("geom2").feature("imp2").set("formsolid", false);
    model.component("comp2").geom("geom2").feature("imp2").set("selresult", true);
    model.component("comp2").geom("geom2").feature("imp2").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").feature("fin").set("repairtoltype", "relative");
    model.component("comp2").geom("geom2").feature("fin").set("repairtol", 1.0E-4);
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("comsel1", "ComplementSelection");
    model.component("comp2").geom("geom2").feature("comsel1").label("\u8f74\u74e6");
    model.component("comp2").geom("geom2").feature("comsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("comsel1").set("input", new String[]{"imp2"});

    model.component("comp2").variable().create("var1");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").variable("var1").label("\u8f74\u627f\u69fd\u53d8\u91cf");
    model.component("comp2").variable("var1").selection().geom("geom2", 2);
    model.component("comp2").variable("var1").selection().named("geom2_imp2_bnd");
    model.component("comp2").variable("var1").set("hfilm", "hg+hf");
    model.component("comp2").variable("var1").descr("hfilm", "\u819c\u539a");
    model.component("comp2").variable().duplicate("var2", "var1");
    model.component("comp2").variable("var2").label("\u8f74\u74e6\u53d8\u91cf");
    model.component("comp2").variable("var2").selection().named("geom2_comsel1");
    model.component("comp2").variable("var2").set("hfilm", "hf");

    model.component("comp2").material().copy("mat2", "mat1");

    model.component("comp2").physics().copy("hdb2", "hdb");
    model.component("comp2").physics("hdb2").feature("htb1").set("hB1", "hfilm");
    model.component("comp2").physics("hdb2").feature("init1")
         .set("pfilm", "100000[Pa]*hdb2.max(hdb2.hB1)/(0.1*hdb2.max(hdb2.hB1)+hdb2.hB1)");

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().remaining();
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "meshsz");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", "meshsz/2");
    model.component("comp2").mesh("mesh2").feature("size").set("hcurve", 10);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/hdb", true);
    model.study("std2").feature("stat").setSolveFor("/physics/hdb2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/hdb", false);
    model.study("std2").feature("stat").setSolveFor("/common/dtopo1", false);
    model.study("std1").feature("stat").setSolveFor("/physics/hdb2", false);
    model.study("std2").label("\u9a8c\u8bc1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6d41\u4f53\u538b\u529b (hdb2)");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("inherittubescale", false);
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").feature("con1").set("inheritplot", "surf1");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316 1");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg5");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 1");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u9608\u503c 1");

    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668 1");
    model.result().dataset("filt2").set("data", "dset3");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result().dataset("filt2").set("level", "surface");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("rangecolormin", 0);
    model.result("pg6").feature("surf1").set("rangecolormax", 1);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(1, 2);
    model.result("pg7").set("data", "filt2");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("titletype", "none");
    model.result("pg5").run();

    model.nodeGroup().remove("grp2");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u76ee\u6807\u6bd4\u8f83");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"hdb.htb1.Fcz"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("gev2").set("expr", new String[]{"hdb2.htb1.Fcz"});
    model.result().evaluationGroup("eg1").feature("gev2")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result().evaluationGroup("eg1").feature("gev2").set("unit", new String[]{"N"});
    model.result().evaluationGroup("eg1").run();
    model.result("pg2").run();

    model.title("\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\u7684\u62d3\u6251\u4f18\u5316");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u5bf9\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\u8fdb\u884c\u62d3\u6251\u4f18\u5316\uff0c\u4ee5\u6700\u5927\u9650\u5ea6\u5730\u63d0\u9ad8\u627f\u8f7d\u529b\u3002\u8fd9\u7c7b\u8f74\u627f\u7531\u4e00\u4e2a\u9636\u68af\u8f74\u627f\u8868\u9762\u7ec4\u6210\uff0c\u8f74\u7684\u7aef\u90e8\u5728\u8be5\u8868\u9762\u4e0a\u65cb\u8f6c\u3002\u6574\u4e2a\u88c5\u914d\u6d78\u5728\u6da6\u6ed1\u6cb9\u4e2d\u3002\u5176\u4e2d\u5047\u8bbe\u8f74\u73af\u5728\u65cb\u8f6c\u65f6\uff0c\u8f74\u627f\u4e2d\u6ca1\u6709\u4efb\u4f55\u8f74\u5411\u8fd0\u52a8\u3002\n\n\u8be5\u6a21\u578b\u8868\u660e\uff0c\u4f7f\u7528\u975e\u5747\u5300\u8bbe\u8ba1\u6765\u521d\u59cb\u5316\u4f18\u5316\uff0c\u53ef\u4ee5\u5728\u7279\u5b9a\u6b65\u6570\u4e0b\u4ea7\u751f\u5c40\u90e8\u6700\u5c0f\u503c\u3002");

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

    model.label("step_thrust_bearing_topology_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
