/*
 * carbon_fibers.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class carbon_fibers {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("w", "4");
    model.param().descr("w", "\u5faa\u73af\u6570");
    model.param().set("n", "2*w-1");
    model.param().descr("n", "\u6bcf\u4e2a\u8f74\u4e0a\u7684\u7ea4\u7ef4\u6570");
    model.param().set("l", "0.2");
    model.param().descr("l", "\u5faa\u73af\u957f\u5ea6");
    model.param().set("amp", "l/4");
    model.param().descr("amp", "\u5faa\u73af\u7684\u5e45\u503c");
    model.param().set("q", "1");
    model.param().descr("q", "\u6a2a\u622a\u9762\u63a7\u5236\u53c2\u6570");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").lengthUnit("cm");
    model.geom("part1").inputParam().set("a", "0.2[mm]");
    model.geom("part1").inputParam().descr("a", "\u6a2a\u622a\u9762\u534a\u8f74");
    model.geom("part1").localParam().set("b", "a");
    model.geom("part1").localParam().descr("b", "\u6a2a\u622a\u9762\u534a\u8f74");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("quickplane", "yz");
    model.geom("part1").feature("wp1").geom().create("e1", "Ellipse");
    model.geom("part1").feature("wp1").geom().feature("e1").set("semiaxes", new String[]{"a", "b"});
    model.geom("part1").label("\u692d\u5706\u6a2a\u622a\u9762");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").lengthUnit("cm");
    model.geom("part2").inputParam().set("a", "0.6[mm]");
    model.geom("part2").localParam().set("b", "a/2");
    model.geom("part2").create("wp1", "WorkPlane");
    model.geom("part2").feature("wp1").set("unite", true);
    model.geom("part2").feature("wp1").set("quickplane", "yz");
    model.geom("part2").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part2").feature("wp1").geom().feature("r1").set("size", new String[]{"a", "b"});
    model.geom("part2").feature("wp1").geom().feature("r1").set("base", "center");
    model.geom("part2").feature("wp1").geom().run("r1");
    model.geom("part2").label("\u77e9\u5f62\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "q==1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("else1", "Else");
    model.component("comp1").geom("geom1").run("else1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").feature("boxsel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u8def\u5f84");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "l/4", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "l/4", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "l/2", 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "amp", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "amp", 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "l/2", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "3*l/4", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "3*l/4", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "l", 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "amp", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", "amp", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ccur1").selection("input")
         .set("cb1", "cb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ccur1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("ccur1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"w", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"l", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").named("csel1");
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").named("csel2");
    model.component("comp1").geom("geom1").feature("swe1").set("keep", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("swe1", 1);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "l/2", 0);
    model.component("comp1").geom("geom1").feature().duplicate("ext2", "ext1");
    model.component("comp1").geom("geom1").runPre("ext2");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 46);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", "l/2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "1", "ceil(n/2)"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "l"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u7ea4\u7ef4\u9635\u5217 1");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"1", "1", "floor(n/2)"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "0", "l"});
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u7ea4\u7ef4\u9635\u5217 2");
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel4");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "l/8", "0"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("arr1", "mir1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"w*l/2", "0", "n/2*l/2-l/4"});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("arr1", "mir1");
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"0", "l/8", "0"});
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("mir2", "rot1");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u7ea4\u7ef4");
    model.component("comp1").geom("geom1").feature("uni1").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l*(w+1)", "5/8*l", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "l*(w+1)", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"(n+1)*l/4", "l/8", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "(n-1)*l/4", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "l/2", 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("blk1", "uni1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1")
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 21, 29, 46, 47, 55, 63, 80, 81, 89, 97, 114, 115, 123, 131, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149);
    model.component("comp1").selection("sel1").label("\u65e0\u9650\u5143\u57df");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("com1").label("\u82af\u57df");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"com1"});
    model.component("comp1").selection("adj1").label("\u82af\u8fb9\u754c");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"com1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel5_dom"});
    model.component("comp1").selection("dif1").label("\u73af\u6c27\u6811\u8102\uff08\u82af\uff09");
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").set("add", new String[]{"geom1_csel5_dom"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"sel1"});
    model.component("comp1").selection("dif2").label("\u7ea4\u7ef4 \uff08\u82af\uff09");
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").set("input", new String[]{"dif2"});
    model.component("comp1").selection("adj2").label("\u7ea4\u7ef4\u8fb9\u754c\uff08\u82af\uff09");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(53);
    model.component("comp1").selection("sel2").label("\u70ed\u6e90\u8fb9\u754c");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(53, 95);
    model.component("comp1").selection("sel3").label("\u51b7\u5374\u8fb9\u754c");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection("sel4")
         .add(9, 49, 52, 55, 57, 59, 62, 65, 74, 77, 80, 83, 122, 212, 294, 384, 466, 556, 638, 707);
    model.component("comp1").selection("sel4").label("\u9762 1");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(6);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection("sel5").add(699, 702, 705, 709, 712, 715, 724, 727, 730, 733);
    model.component("comp1").selection("sel5").label("\u9762 2");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"int1"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"sel4"});
    model.component("comp1").selection("dif3").label("\u5165\u53e3");
    model.component("comp1").selection().create("dif4", "Difference");
    model.component("comp1").selection("dif4").set("entitydim", 2);
    model.component("comp1").selection("dif4").set("add", new String[]{"int1"});
    model.component("comp1").selection("dif4").set("subtract", new String[]{"sel5"});
    model.component("comp1").selection("dif4").label("\u51fa\u53e3");
    model.component("comp1").selection().create("dif5", "Difference");
    model.component("comp1").selection("dif5").set("entitydim", 2);
    model.component("comp1").selection("dif5").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif5").set("subtract", new String[]{"dif3", "dif4"});
    model.component("comp1").selection("dif5").label("\u73af\u6c27\u6811\u8102\u8fb9\u754c\uff08\u82af\uff09");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection("sel6")
         .add(1, 3, 4, 7, 10, 11, 14, 17, 26, 29, 32, 35, 51, 58, 99, 123, 205, 213, 271, 295, 377, 385, 443, 467, 549, 557, 615, 639, 701, 708, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756);
    model.component("comp1").selection("sel6").label("\u6e29\u5ea6\u8fb9\u754c");
    model.component("comp1").selection().create("dif6", "Difference");
    model.component("comp1").selection("dif6").set("entitydim", 2);
    model.component("comp1").selection("dif6").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif6").set("subtract", new String[]{"dif3", "dif4"});
    model.component("comp1").selection("dif6").label("\u7ea4\u7ef4\u58c1");
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").set("entitydim", 2);
    model.component("comp1").selection("adj3").set("input", new String[]{"dif3"});
    model.component("comp1").selection("adj3").set("outputdim", 1);
    model.component("comp1").selection("adj3").label("\u5165\u53e3\u8fb9");
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").set("input", new String[]{"sel1", "geom1_csel5_dom"});
    model.component("comp1").selection("int2").label("\u7ea4\u7ef4\uff08\u65e0\u9650\u5143\u57df\uff09");

    model.title("\u53c2\u6570\u5316\u78b3\u7ea4\u7ef4\u7f16\u7ec7\u7ed3\u6784\u51e0\u4f55");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u78b3\u7ea4\u7ef4\u7f16\u7ec7\u7ed3\u6784\u7684\u5404\u5411\u5f02\u6027\u4f20\u70ed\u201d\u793a\u4f8b\u7684\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.mesh().clearMeshes();

    model.label("carbon_fibers_geom.mph");

    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");
    model.component("comp1").physics("cc").selection().named("dif2");
    model.component("comp1").physics("cc").prop("Settings").set("CreateBasis", true);
    model.component("comp1").physics("cc").feature("css1").set("SecondVector", "y");
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 3);
    model.component("comp1").physics("cc").feature("diff1").selection().named("dif2");
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().named("dif3");
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().named("dif4");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("dif3");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().named("adj3");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(12);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u77e2\u91cf\u573a (cc)");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.1);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5750\u6807\u7cfb (cc)");
    model.result("pg2").create("sys1", "CoordSysVolume");
    model.result("pg2").feature("sys1").set("sys", "cc_cs");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().set(20, 34, 54, 68, 88, 102, 122, 136);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("sys1").set("xnumber", 16);
    model.result("pg2").feature("sys1").set("ynumber", 2);
    model.result("pg2").feature("sys1").set("znumber", 2);
    model.result("pg2").run();

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().named("dif2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("Q_in", "1e5[W/m^2]*exp(-5e6[1/m^2]*((x-mass1.CMX)^2+(z-mass1.CMZ)^2))");
    model.component("comp1").variable("var1").descr("Q_in", "\u8fb9\u754c\u70ed\u6e90");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u73af\u6c27\u6811\u8102");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1200"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"1000"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78b3\u7ea4\u7ef4");
    model.component("comp1").material("mat2").selection().named("geom1_csel5_dom");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"60", "4", "4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1500"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"1000"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u78b3\u7ea4\u7ef4\uff08\u65e0\u9650\u5143\u57df\uff09");
    model.component("comp1").material("mat3").selection().named("int2");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"60"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1500"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"1000"});

    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid2").selection().named("dif2");
    model.component("comp1").physics("ht").feature("solid2").set("coordinateSystem", "cc_cs");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 2);
    model.component("comp1").physics("ht").feature("bhs1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input", "Q_in");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 10);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel6");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().named("dif6");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e2d\u95f4\u5207\u9762\u7684\u6e29\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "T");
    model.result("pg4").feature("slc1").set("descr", "\u6e29\u5ea6");
    model.result("pg4").feature("slc1").set("quickplane", "zx");
    model.result("pg4").feature("slc1").set("quickynumber", 1);
    model.result("pg4").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("slc1").create("sel1", "Selection");
    model.result("pg4").feature("slc1").feature("sel1").selection().named("com1");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();

    model.title("\u78b3\u7ea4\u7ef4\u7f16\u7ec7\u7ed3\u6784\u7684\u5404\u5411\u5f02\u6027\u4f20\u70ed");

    model
         .description("\u78b3\u7ea4\u7ef4\u589e\u5f3a\u805a\u5408\u7269\u5305\u542b\u78b3\u7ea4\u7ef4\u7f16\u7ec7\u7ed3\u6784\uff0c\u5176\u4e2d\u7684\u78b3\u7ea4\u7ef4\u5728\u6cbf\u8f74\u65b9\u5411\u7684\u70ed\u5bfc\u7387\u8fdc\u9ad8\u4e8e\u5782\u76f4\u65b9\u5411\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u66f2\u7ebf\u5750\u6807\u201d\u63a5\u53e3\u8ba1\u7b97\u5c40\u90e8\u7ea4\u7ef4\u65b9\u5411\uff0c\u4ee5\u53ca\u5982\u4f55\u7528\u5b83\u6765\u5b9a\u4e49\u7ea4\u7ef4\u7684\u5404\u5411\u5f02\u6027\u70ed\u5bfc\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").clearSolutionData();

    model.label("carbon_fibers.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
