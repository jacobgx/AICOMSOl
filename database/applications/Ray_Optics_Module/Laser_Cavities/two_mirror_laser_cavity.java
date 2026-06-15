/*
 * two_mirror_laser_cavity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:15 by COMSOL 6.3.0.290. */
public class two_mirror_laser_cavity {

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
    model.param().set("R", "1[m]", "\u66f2\u7387\u534a\u5f84");
    model.param().set("L", "0.2[m]", "\u8154\u4f53\u957f\u5ea6");
    model.param().set("D", "25[mm]", "\u53cd\u5c04\u955c\u76f4\u5f84");
    model.param().set("T0", "1[us]", "\u603b\u8ba1\u7b97\u65f6\u95f4");
    model.param().set("th", "1e-3[deg]", "\u521d\u59cb\u5c04\u7ebf\u89d2\u5ea6");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0);
    model.component("comp1").func("rect1").set("upper", "2*R");
    model.component("comp1").func("rect1").set("smooth", 0.001);

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\spherical_mirror_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R", "-R");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "12[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "D");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hole", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_cylsel1", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "csel1", "none", "none", "none"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", 1);
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "L"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"sin(th)", "0", "cos(th)"});
    model.component("comp1").physics("gop").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("gop").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "D/20");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.2,0.2,2.4)", 0);
    model.study("std1").feature("rtrac").set("tlist", "0 T0");
    model.study("std1").feature("rtrac").set("raystopcond", "noactive");
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

    model.component("comp1").geom("geom1").run();

    model.result("pg1").run();
    model.result("pg1").set("outersolnum", 1);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5c04\u7ebf\u8f68\u8ff9\uff0cL=0.2");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "cyan");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "ray1");
    model.result("pg2").set("innerinput", "last");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7a33\u5b9a\u6027");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6fc0\u5149\u8154\u7a33\u5b9a\u6027\u5206\u6790");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "t/T0", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u5c04\u7ebf\u8ffd\u8e2a", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "L");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linecolor", "blue");
    model.result("pg2").feature("glob1").set("linemarker", "diamond");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5c04\u7ebf\u8ffd\u8e2a", 0);
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "rect1(L)", 0);
    model.result("pg2").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg2").feature("glob2").setIndex("descr", "ABCD \u7406\u8bba", 0);
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "L");
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("linecolor", "red");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "ABCD \u7406\u8bba", 0);
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u53cc\u955c\u6fc0\u5149\u8154");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u7b80\u5355\u7684\u6fc0\u5149\u8154\u6a21\u578b\uff0c\u5b83\u7531\u4e24\u4e2a\u7403\u9762\u955c\u7ec4\u6210\u3002\u6211\u4eec\u9996\u5148\u5c06\u4e00\u675f\u5149\u7ebf\u4ee5\u76f8\u5bf9\u4e8e\u5149\u8f74\u8f83\u5c0f\u7684\u89d2\u5ea6\u91ca\u653e\u51fa\u6765\uff0c\u7136\u540e\u8ffd\u8e2a\u5176\u53d1\u751f\u5927\u91cf\u53cd\u5c04\u7684\u60c5\u51b5\uff0c\u4ee5\u786e\u5b9a\u8be5\u5149\u7ebf\u662f\u5426\u4ecd\u88ab\u9650\u5236\u5728\u8154\u5185\u3002\u672c\u4f8b\u9488\u5bf9\u591a\u79cd\u4e0d\u540c\u5c3a\u5bf8\u7684\u8154\u4f53\u91cd\u590d\u6267\u884c\u8be5\u7814\u7a76\uff0c\u7136\u540e\u5c06\u7a33\u5b9a\u6027\u5206\u6790\u7ed3\u679c\u4e0e ABCD \u77e9\u9635\u7406\u8bba\u8fdb\u884c\u6bd4\u8f83\u3002");

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

    model.label("two_mirror_laser_cavity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
