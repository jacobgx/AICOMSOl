/*
 * compact_camera_module.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:15 by COMSOL 6.3.0.290. */
public class compact_camera_module {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().label("\u53c2\u6570 1\uff1a\u900f\u955c\u89c4\u683c\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u901a\u7528");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lambda", "587.56[nm]", "\u771f\u7a7a\u6ce2\u957f");
    model.param("par2").set("nref1", "1.544", "\u900f\u955c 1 \u548c 4 \u7684\u6298\u5c04\u7387 (587.56[nm])");
    model.param("par2")
         .set("nref2", "1.632", "\u900f\u955c 2\u30013 \u548c 5 \u7684\u6298\u5c04\u7387 (587.56[nm])");
    model.param("par2").set("nref3", "1.516", "\u7ea2\u5916\u6ee4\u955c\u6298\u5c04\u7387 (587.56[nm])");
    model.param("par2").set("D_pupil", "2.50[mm]", "\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param("par2").set("N_ring", "25", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("theta1", "0[deg]", "\u89c6\u573a\u89d2 1");
    model.param("par2").set("theta2", "5[deg]", "\u89c6\u573a\u89d2 2");
    model.param("par2").set("theta3", "10[deg]", "\u89c6\u573a\u89d2 3");
    model.param("par2").set("theta4", "15[deg]", "\u89c6\u573a\u89d2 4");
    model.param("par2").set("dz", "-0.5[mm]", "\u5c04\u7ebf\u91ca\u653e z \u5750\u6807");

    model.component("comp1").geom("geom1").label("\u5fae\u578b\u76f8\u673a\u6a21\u7ec4");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("compact_camera_module_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(3, 4);
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"nref1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2, 5, 6);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nref2"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(1);
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"nref3"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AirEdlen1953", 0);
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lambda");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf\u9762");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_pi8_sel1");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"0", "dz*tan(theta1)", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "D_pupil/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"0", "tan(theta1)", "1"});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"0", "dz*tan(theta2)", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"0", "tan(theta2)", "1"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"0", "dz*tan(theta3)", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"0", "tan(theta3)", "1"});
    model.component("comp1").physics("gop").feature().duplicate("relg4", "relg3");
    model.component("comp1").physics("gop").feature("relg4").set("qcc", new String[]{"0", "dz*tan(theta4)", "dz"});
    model.component("comp1").physics("gop").feature("relg4").set("L0", new String[]{"0", "tan(theta4)", "1"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 10");
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
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe 1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.prf");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,abs(x))<0.01[mm]");
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "abs(y)");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("coloring", "gradient");
    model.result("pg1").feature("slc1").set("topcolor", "black");
    model.result("pg1").feature("slc1").set("bottomcolor", "white");
    model.result("pg1").feature("slc1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5c04\u7ebf\u56fe 2");
    model.result("pg2").set("view", "new");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg2").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("filt1").set("type", "all");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").active(false);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_csel3_bnd");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "gop.nref_local");
    model.result("pg2").feature("surf1").set("coloring", "gradient");
    model.result("pg2").feature("surf1").set("topcolor", "custom");
    model.result("pg2").feature("surf1")
         .set("customtopcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg2").feature("surf1").set("bottomcolor", "custom");
    model.result("pg2").feature("surf1").set("custombottomcolor", new double[]{0.8784313797950745, 1, 1});
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u70b9\u5217\u56fe");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("spot1", "SpotDiagram");
    model.result("pg3").feature("spot1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg3").run();

    model.title("\u5fae\u578b\u76f8\u673a\u6a21\u7ec4");

    model
         .description("\u5fae\u578b\u76f8\u673a\u6a21\u7ec4\u5e7f\u6cdb\u7528\u4e8e\u624b\u673a\u548c\u5e73\u677f\u7535\u8111\u7b49\u7535\u5b50\u8bbe\u5907\u3002\u4e3a\u4e86\u51cf\u5c0f\u6240\u9700\u5143\u4ef6\u7684\u5c3a\u5bf8\u548c\u6570\u91cf\uff0c\u5149\u5b66\u8bbe\u8ba1\u4e2d\u901a\u5e38\u5305\u542b\u591a\u4e2a\u9ad8\u5ea6\u975e\u7403\u9762\u8868\u9762\u3002\u6b64\u6a21\u578b\u6f14\u793a\u4e86\u4f7f\u7528\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u96f6\u4ef6\u5e93\u4e2d\u7684\u201c\u5076\u6b21\u975e\u7403\u9762\u900f\u955c\uff08\u4e09\u7ef4\uff09\u201d\u96f6\u4ef6\u8fdb\u884c\u4e94\u5143\u4ef6\u8bbe\u8ba1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("compact_camera_module.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
