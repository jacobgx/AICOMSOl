/*
 * achromatic_phase_shifter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:19 by COMSOL 6.3.0.290. */
public class achromatic_phase_shifter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Prisms_and_Coatings");

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
    model.param().set("lam0", "600[nm]", "\u81ea\u7531\u7a7a\u95f4\u6ce2\u957f");
    model.param().set("np", "1.509", "\u68f1\u955c\u6298\u5c04\u7387");
    model.param().set("nf1", "2.0535", "\u6298\u5c04\u7387\uff0c\u5c42 1");
    model.param().set("nf2", "1.496", "\u6298\u5c04\u7387\uff0c\u5c42 2");
    model.param().set("nf3", "1.664", "\u6298\u5c04\u7387\uff0c\u5c42 3");
    model.param().set("nf4", "2.346", "\u6298\u5c04\u7387\uff0c\u5c42 4");
    model.param().set("tf1", "0.089*lam0", "\u539a\u5ea6\uff0c\u5c42 1");
    model.param().set("tf2", "0.3856*lam0", "\u539a\u5ea6\uff0c\u5c42 2");
    model.param().set("tf3", "0.111*lam0", "\u539a\u5ea6\uff0c\u5c42 3");
    model.param().set("tf4", "0.0447*lam0", "\u539a\u5ea6\uff0c\u5c42 4");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1, 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"np"});

    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.2, 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.5, 1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.5, 2);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{1, 0, 0});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1")
         .setIndex("lambda0vals", "range(lam0/0.8,(lam0/1.3-(lam0/0.8))/99,lam0/1.3)", 0);
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarizationType", "FullyPolarized");
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarization", "UserDefined");
    model.component("comp1").physics("gop").feature("relg1").set("a20", 1);
    model.component("comp1").physics("gop").create("matd2", "MaterialDiscontinuity", 2);
    model.component("comp1").physics("gop").feature("matd2").selection().set(2);
    model.component("comp1").physics("gop").feature("matd2").label("\u5355\u5c42\u6d82\u5c42");
    model.component("comp1").physics("gop").feature("matd2")
         .set("ThinDielectricFilmsOnBoundary", "AddLayersToSurface");
    model.component("comp1").physics("gop").feature("matd2").create("film1", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd2").feature("film1").set("nf", "nf1");
    model.component("comp1").physics("gop").feature("matd2").feature("film1").set("tf", "tf1");
    model.component("comp1").physics("gop").create("matd3", "MaterialDiscontinuity", 2);
    model.component("comp1").physics("gop").feature("matd3").selection().set(2);
    model.component("comp1").physics("gop").feature("matd3").label("\u4e09\u5c42\u6d82\u5c42");
    model.component("comp1").physics("gop").feature("matd3")
         .set("ThinDielectricFilmsOnBoundary", "AddLayersToSurface");
    model.component("comp1").physics("gop").feature("matd3").create("film1", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd3").feature("film1").set("nf", "nf2");
    model.component("comp1").physics("gop").feature("matd3").feature("film1").set("tf", "tf2");
    model.component("comp1").physics("gop").feature("matd3").create("film2", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd3").feature("film2").set("nf", "nf3");
    model.component("comp1").physics("gop").feature("matd3").feature("film2").set("tf", "tf3");
    model.component("comp1").physics("gop").feature("matd3").create("film3", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd3").feature("film3").set("nf", "nf4");
    model.component("comp1").physics("gop").feature("matd3").feature("film3").set("tf", "tf4");

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 0.6");
    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"gop/matd3"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u504f\u632f\u692d\u5706\uff0c\u5355\u5c42\u6d82\u5c42");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("pointtype", "ellipse");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.s3/gop.s0");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u4f4d\u5ef6\u8fdf vs. \u6ce2\u957f");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5355\u5c42\u548c\u4e09\u5c42\u819c\u7684\u76f8\u4f4d\u5ef6\u8fdf");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\\lambda<sub>0</sub>/\\lambda");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u76f8\u4f4d\u5ef6\u8fdf (deg)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "gop.delta");
    model.result("pg2").feature("rtp1").set("unit", "\u00b0");
    model.result("pg2").feature("rtp1").set("xdata", "expr");
    model.result("pg2").feature("rtp1").set("xdataexpr", "lam0/gop.lambda0");
    model.result("pg2").feature("rtp1").set("legend", true);
    model.result("pg2").feature("rtp1").set("legendmethod", "manual");
    model.result("pg2").feature("rtp1").setIndex("legends", "Single-layer coating", 0);
    model.result("pg2").feature("rtp1").setIndex("legends", "\u5355\u5c42\u6d82\u5c42", 0);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "0 0.6");
    model.study("std2").feature("rtrac").set("useadvanceddisable", true);
    model.study("std2").feature("rtrac").set("disabledphysics", new String[]{"gop/matd2"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol2");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "ray2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg3").create("rtrj1", "RayTrajectories");
    model.result("pg3").feature("rtrj1").set("linetype", "line");
    model.result("pg3").feature("rtrj1").create("col1", "Color");
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg3").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg3").run();
    model.result("pg3").label("\u504f\u632f\u692d\u5706\uff0c\u4e09\u5c42\u6d82\u5c42");
    model.result("pg3").run();
    model.result("pg3").feature("rtrj1").set("pointtype", "ellipse");
    model.result("pg3").run();
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "gop.s3/gop.s0");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("rtp2", "rtp1");
    model.result("pg2").run();
    model.result("pg2").feature("rtp2").set("data", "ray2");
    model.result("pg2").feature("rtp2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("rtp2").setIndex("legends", "\u4e09\u5c42\u6d82\u5c42", 0);
    model.result("pg2").run();

    model.title("\u5168\u5185\u53cd\u5c04\u8584\u819c\u65e0\u8272\u6563\u79fb\u76f8\u5668 (TIRTF APS)");

    model
         .description("\u5bf9\u4e8e\u5404\u79cd\u5149\u5b66\u5668\u4ef6\uff0c\u6539\u53d8\u5149\u7684\u504f\u632f\u8fd9\u4e00\u80fd\u529b\u81f3\u5173\u91cd\u8981\u3002\u4f8b\u5982\uff0c\u5149\u7684\u504f\u632f\u5bf9\u5149\u9891\u9694\u79bb\u5668\u3001\u8870\u51cf\u5668\u548c\u5206\u675f\u5668\u7684\u6027\u80fd\u6709\u663e\u8457\u5f71\u54cd\u3002\u901a\u8fc7\u4e3a\u5149\u6307\u5b9a\u7279\u5b9a\u7684\u504f\u632f\uff08\u4e3b\u8981\u662f\u7ebf\u6027\u504f\u632f\u6216\u5706\u504f\u632f\uff09\uff0c\u53ef\u4ee5\u663e\u8457\u51cf\u5c11\u5149\u5b66\u7cfb\u7edf\u4e2d\u7684\u7729\u5149\u3002\n\n\u64cd\u63a7\u504f\u632f\u7684\u6700\u57fa\u672c\u65b9\u6cd5\u4e4b\u4e00\u662f\u6ce2\u5ef6\u8fdf\uff0c\u5728\u8be5\u65b9\u6cd5\u4e2d\uff0c\u7535\u573a\u7684\u4e00\u4e2a\u5206\u91cf\u76f8\u5bf9\u4e8e\u4f20\u64ad\u5149\u675f\u4e2d\u7684\u6b63\u4ea4\u7535\u573a\u5206\u91cf\u4ea7\u751f\u76f8\u4f4d\u5ef6\u8fdf\u3002\u5f53\u5149\u5728\u8868\u9762\u53d1\u751f\u5168\u5185\u53cd\u5c04\u65f6\uff0c\u4f1a\u4ea7\u751f\u76f8\u4f4d\u5ef6\u8fdf\u3002\u7535\u573a\u5206\u91cf\u4e4b\u95f4\u7684\u76f8\u5dee\u53d7\u5230\u53cd\u5c04\u9762\u4e0a\u5b58\u5728\u7684\u4ecb\u7535\u8584\u819c\u7684\u5f71\u54cd\u3002\n\n\u8be5\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u5355\u5c42\u548c\u4e09\u5c42\u6d82\u5c42\u7684\u76f8\u4f4d\u5ef6\u8fdf\u89d2\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u53d1\u8868\u7684\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002\u8be5\u539f\u7406\u53ef\u7528\u4e8e\u8bbe\u8ba1\u5728\u5bbd\u5149\u8c31\u8303\u56f4\u5185\u76f8\u4f4d\u5ef6\u8fdf\u51e0\u4e4e\u5747\u5300\u7684\u5168\u5185\u53cd\u5c04\u8584\u819c\u65e0\u8272\u6563\u79fb\u76f8\u5668 (TIRTF APS)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("achromatic_phase_shifter.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
