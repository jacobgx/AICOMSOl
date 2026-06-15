/*
 * bow_tie_laser_cavity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:15 by COMSOL 6.3.0.290. */
public class bow_tie_laser_cavity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Laser_Cavities");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "0.1[m]", "\u5e73\u9762\u955c\u534a\u8ddd\u79bb");
    model.param().set("L2", "0.05[m]", "\u7403\u9762\u955c\u534a\u8ddd\u79bb");
    model.param().set("th", "10[deg]", "\u5e73\u9762\u955c\u503e\u89d2");
    model.param()
         .set("dth", "0.1[deg]", "\u4e0e\u5e73\u9762\u955c\u6cd5\u7ebf\u6240\u6210\u7684\u521d\u59cb\u5c04\u7ebf\u89d2\u5ea6");
    model.param().set("T0", "1[us]", "\u603b\u8ba1\u7b97\u65f6\u95f4");
    model.param().set("dt", "T0/200", "\u65f6\u95f4\u589e\u91cf");
    model.param().set("D_FM", "12.5[mm]", "\u5e73\u9762\u955c\u76f4\u5f84");
    model.param().set("L_FM", "10[mm]", "\u5e73\u9762\u955c\u539a\u5ea6");
    model.param().set("X_FM", "-L1*tan(th)", "\u5e73\u9762\u955c X \u4f4d\u7f6e");
    model.param().set("Y_FM", "0[m]", "\u5e73\u9762\u955c Y \u4f4d\u7f6e");
    model.param().set("Z_FM", "-L1", "\u5e73\u9762\u955c Z \u4f4d\u7f6e");
    model.param().set("RX_FM", "0[deg]", "\u5e73\u9762\u955c X \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("RY_FM", "th", "\u5e73\u9762\u955c Y \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("RZ_FM", "0[deg]", "\u5e73\u9762\u955c Z \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("D_SM", "12.5[mm]", "\u7403\u9762\u955c\u76f4\u5f84");
    model.param().set("L_SM", "10[mm]", "\u7403\u9762\u955c\u539a\u5ea6");
    model.param().set("R_SM", "0.5[m]", "\u7403\u9762\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("X_SM", "L2*tan(th)", "\u7403\u9762\u955c X \u4f4d\u7f6e");
    model.param().set("Y_SM", "0[m]", "\u7403\u9762\u955c Y \u4f4d\u7f6e");
    model.param().set("Z_SM", "L2", "\u7403\u9762\u955c Z \u4f4d\u7f6e");
    model.param().set("RX_SM", "0[deg]", "\u7403\u9762\u955c X \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("RY_SM", "th/2", "\u7403\u9762\u955c Y \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("RZ_SM", "0[deg]", "\u7403\u9762\u955c Z \u65b9\u5411\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("wl", "0.78[um]", "\u6ce2\u957f");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "0.04*(51200*x^4-40960*x^3+8832*x^2-256*x-23)");
    model.component("comp1").func("an1").setIndex("plotargs", 0.6, 0, 2);
    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0);
    model.component("comp1").func("rect1").set("upper", 0.15);
    model.component("comp1").func("rect1").set("smooth", 0.001);
    model.component("comp1").func().duplicate("rect2", "rect1");
    model.component("comp1").func("rect2").set("lower", 0.25);
    model.component("comp1").func("rect2").set("upper", 0.455);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("expr", "rect1(x)+rect2(x)");
    model.component("comp1").func("an2").setIndex("plotargs", 0.6, 0, 2);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "D_FM/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_FM");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"X_FM", "Y_FM", "Z_FM"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "spherical");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax2", new String[]{"180+th", "0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\spherical_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "-R_SM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "10[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "D_SM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "D_SM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", "D_SM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", "sin(RY_SM)");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", "cos(RY_SM)");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"X_SM", "Y_SM", "Z_SM"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("cyl1", "pi1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "wl");
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").selection().set(7, 8, 15, 18);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "X_FM", 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "Y_FM", 1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "Z_FM", 2);
    model.component("comp1").physics("gop").feature("relg1")
         .set("L0", new String[]{"sin(th+dth)", "0", "cos(th+dth)"});
    model.component("comp1").physics("gop").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("gop").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "D_SM/20");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "D_SM/40");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("tlist", "range(0,dt,T0)");
    model.study("std1").feature("rtrac").set("raystopcond", "noactive");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L2", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "range(0.02,0.02,0.14) range(0.142,0.002,0.152) range(0.16,0.02,0.24) range(0.242,0.002,0.252) range(0.26,0.02,0.44) range(0.442,0.002,0.452) range(0.46,0.02,0.6)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").set("outersolnum", 6);
    model.result("pg1").set("solnum", 201);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "cyan");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "ray1");
    model.result("pg2").set("innerinput", "last");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7a33\u5b9a\u6027");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6fc0\u5149\u8154\u7a33\u5b9a\u6027\u5206\u6790");
    model.result("pg2").set("legendpos", "middleleft");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "t/T0", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u5c04\u7ebf\u8ffd\u8e2a", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "L2");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linecolor", "blue");
    model.result("pg2").feature("glob1").set("linemarker", "diamond");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5c04\u7ebf\u8ffd\u8e2a", 0);
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "an2(L2)", 0);
    model.result("pg2").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg2").feature("glob2").setIndex("descr", "ABCD \u7406\u8bba", 0);
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "L2");
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("linecolor", "red");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "ABCD \u7406\u8bba", 0);
    model.result("pg2").run();

    model.title("\u8774\u8776\u7ed3\u578b\u6fc0\u5149\u8154");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u5bf9\u79f0\u8774\u8776\u7ed3\u7684\u6784\u578b\u7684\u6fc0\u5149\u8154\u6a21\u578b\uff0c\u5b83\u7531\u4e24\u4e2a\u7403\u9762\u955c\u548c\u4e24\u4e2a\u5e73\u9762\u955c\u7ec4\u6210\u3002\u6211\u4eec\u9996\u5148\u5c06\u4e00\u675f\u5149\u7ebf\u4ee5\u76f8\u5bf9\u4e8e\u5149\u8f74\u8f83\u5c0f\u7684\u89d2\u5ea6\u53d1\u5c04\u51fa\u6765\uff0c\u7136\u540e\u8ffd\u8e2a\u5176\u53d1\u751f\u5927\u91cf\u53cd\u5c04\u7684\u60c5\u51b5\uff0c\u4ee5\u786e\u5b9a\u8be5\u5149\u7ebf\u662f\u5426\u4ecd\u88ab\u9650\u5236\u5728\u8154\u5185\u3002\u672c\u4f8b\u9488\u5bf9\u591a\u79cd\u4e0d\u540c\u5c3a\u5bf8\u7684\u8154\u4f53\u91cd\u590d\u6267\u884c\u8be5\u7814\u7a76\uff0c\u7136\u540e\u5c06\u7a33\u5b9a\u6027\u5206\u6790\u7ed3\u679c\u4e0e ABCD \u77e9\u9635\u7406\u8bba\u8fdb\u884c\u6bd4\u8f83\u3002");

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
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();

    model.label("bow_tie_laser_cavity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
