/*
 * space_frame_instability.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:26 by COMSOL 6.3.0.290. */
public class space_frame_instability {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("P", "1", "\u8f7d\u8377");
    model.param().set("l1", "69.28", "\u503e\u659c\u6784\u4ef6\u5728 x \u8f74\u4e0a\u7684\u6295\u5f71\u957f\u5ea6");
    model.param().set("l2", "61.44", "\u9876\u90e8\u7684\u9762\u5185\u6784\u4ef6\u957f\u5ea6");
    model.param().set("b", "30", "\u6846\u67b6\u5bbd\u5ea6");
    model.param().set("h1", "40", "\u6846\u67b6\u9ad8\u5ea6");
    model.param().set("A1", "0.5", "1 \u7c7b\u6784\u4ef6\u7684\u9762\u79ef");
    model.param()
         .set("Iy1", "0.4", "1 \u7c7b\u6784\u4ef6\u7ed5\u5c40\u90e8 y \u8f74\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param()
         .set("Iz1", "0.133", "1 \u7c7b\u6784\u4ef6\u7ed5\u5c40\u90e8 z \u8f74\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param().set("A2", "0.1", "2 \u7c7b\u6784\u4ef6\u7684\u9762\u79ef");
    model.param()
         .set("Iy2", "0.05", "2 \u7c7b\u6784\u4ef6\u7ed5\u5c40\u90e8 y \u8f74\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param()
         .set("Iz2", "0.05", "2 \u7c7b\u6784\u4ef6\u7ed5\u5c40\u90e8 z \u8f74\u7684\u9762\u79ef\u60ef\u6027\u77e9");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "-l1-l2/2 -l2/2 -l2/2 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "-b/2 -b/2 -b/2 -b/2");
    model.component("comp1").geom("geom1").feature("pol1").set("z", "0 h1 h1 h1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-l2/2", "-b/2", "h1"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"-l2/2", "0", "h1"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("ls1", "pol1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"-l2/2", "0", "h1"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("ls1", "mir1", "pol1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("beam").feature("emm1").set("IsotropicOption", "EG");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EG", "EG", "Young's_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("EG").set("E", new String[]{"4.32e5"});
    model.component("comp1").material("mat1").propertyGroup("EG").set("G", new String[]{"1.66e5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").physics("beam").feature("csd1").set("area", "A1");
    model.component("comp1").physics("beam").feature("csd1").set("Izz", "Iz1");
    model.component("comp1").physics("beam").feature("csd1").set("Iyy", "Iy1");
    model.component("comp1").physics("beam").feature("csd1").set("J_beam", "Iy1+Iz1");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").create("csd2", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd2").selection().set(3, 5, 9, 11);
    model.component("comp1").physics("beam").feature("csd2").set("area", "A2");
    model.component("comp1").physics("beam").feature("csd2").set("Izz", "Iz2");
    model.component("comp1").physics("beam").feature("csd2").set("Iyy", "Iy2");
    model.component("comp1").physics("beam").feature("csd2").set("J_beam", "Iy2+Iz2");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("vector_beam", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").create("pin1", "Pinned", 0);
    model.component("comp1").physics("beam").feature("pin1").selection().set(1, 2, 11, 12);
    model.component("comp1").physics("beam").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl1").selection().set(3, 5, 8, 10);
    model.component("comp1").physics("beam").feature("pl1").set("forcePoint", new String[]{"0", "0", "-P"});
    model.component("comp1").physics("beam").create("pl2", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl2").selection().set(3, 8);
    model.component("comp1").physics("beam").feature("pl2").set("forcePoint", new String[]{"0", "0.001*P", "0"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "P", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "P", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1,8) range(8.02, 0.02, 8.65)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_beam_uLin").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_beam_thLin").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_beam_thLin").set("scaleval", "pi/10");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 40);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1beam");
    model.result("pg1").setIndex("looplevel", 113, 0);
    model.result("pg1").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb (beam)");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "beam.disp");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8f7d\u8377 vs. \u4f4d\u79fb");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "v");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "P");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u8f7d\u8377 vs. \u4f4d\u79fb");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "P");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "beam.uLinY");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "COMSOL", 0);
    model.result("pg2").feature("ptgr1").set("linewidth", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("space_frame_instability_data.txt");
    model.result().table("tbl1").label("\u53c2\u8003\u6570\u636e");
    model.result("pg2").run();
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linemarker", "cycle");
    model.result("pg2").feature("tblp1").set("markerpos", "interp");
    model.result("pg2").feature("tblp1").set("markers", 20);
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u53c2\u8003\u6570\u636e", 0);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/beam", true);
    model.study("std2").create("buckling", "LinearBuckling");
    model.study("std2").feature("buckling").set("plotgroup", "Default");
    model.study("std2").feature("buckling").set("neigsactive", true);
    model.study("std2").feature("buckling").set("solnum", "auto");
    model.study("std2").feature("buckling").set("notsolnum", "auto");
    model.study("std2").feature("buckling").set("outputmap", new String[]{});
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").setSolveFor("/physics/beam", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("frametype", "spatial");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u632f\u578b (beam)");
    model.result("pg3").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u7a7a\u95f4\u62f1\u5f62\u6846\u67b6\u7684\u4e0d\u7a33\u5b9a\u6027");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u4e86\u4e00\u4e2a\u7a7a\u95f4\u62f1\u5f62\u6846\u67b6\u5728\u96c6\u4e2d\u5782\u76f4\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u4e0d\u7a33\u5b9a\u6027\u3002\u5bf9\u8be5\u6846\u67b6\u65bd\u52a0\u4e00\u4e2a\u5f88\u5c0f\u7684\u6a2a\u5411\u8f7d\u8377\u5c31\u4f1a\u4f7f\u7ed3\u6784\u5931\u53bb\u5bf9\u79f0\u6027\u3002\u6211\u4eec\u4f7f\u7528\u51e0\u4f55\u975e\u7ebf\u6027\u6881\u5355\u5143\u5bf9\u6846\u67b6\u4e2d\u7684\u6784\u4ef6\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u7ed3\u679c\u4e0e\u73b0\u6709\u6587\u732e\u6570\u636e\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("space_frame_instability.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
