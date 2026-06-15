/*
 * linear_wave_retarder.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:24 by COMSOL 6.3.0.290. */
public class linear_wave_retarder {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("delta", "0");
    model.param().descr("delta", "\u5ef6\u8fdf\u6027");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 3);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").create("lpol1", "LinearPolarizer", 2);
    model.component("comp1").physics("gop").feature("lpol1").selection().set(1);
    model.component("comp1").physics("gop").create("lpol2", "LinearPolarizer", 2);
    model.component("comp1").physics("gop").feature("lpol2").selection().set(3);
    model.component("comp1").physics("gop").feature("lpol2").set("Ta", new int[]{0, 1, 0});
    model.component("comp1").physics("gop").create("lwav1", "LinearWaveRetarder", 2);
    model.component("comp1").physics("gop").feature("lwav1").selection().set(2);
    model.component("comp1").physics("gop").feature("lwav1").set("Fa", new int[]{1, 1, 0});
    model.component("comp1").physics("gop").feature("lwav1").set("deltal", "delta");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "delta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "delta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 pi/2 pi", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "range(0,0.1,4)");
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
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").label("\u65e0\u6ce2\u5ef6\u8fdf\u5668");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("linetype", "tube");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("pointtype", "ellipse");
    model.result("pg1").feature("rtrj1").set("ellipsecount", 25);
    model.result("pg1").feature("rtrj1").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("rtrj1").set("ellipsearrowscale", 0.3);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u56db\u5206\u4e4b\u4e00\u6ce2\u5ef6\u8fdf\u5668");
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u534a\u6ce2\u5ef6\u8fdf\u5668");
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").run();

    model.title("\u7ebf\u504f\u632f\u6ce2\u5ef6\u8fdf\u5668");

    model
         .description("\u504f\u632f\u5668\u548c\u6ce2\u5ef6\u8fdf\u5668\u7b49\u5149\u5b66\u5668\u4ef6\u7684\u7ec4\u5408\u53ef\u7528\u4e8e\u63a7\u5236\u7a7f\u900f\u8f90\u5c04\u7684\u5f3a\u5ea6\u548c\u504f\u632f\u3002\u672c\u6559\u7a0b\u91c7\u7528\u5177\u6709\u6b63\u4ea4\u900f\u5c04\u8f74\u7684\u4e24\u4e2a\u7ebf\u6027\u504f\u632f\u5668\u5c06\u5149\u7ebf\u5f3a\u5ea6\u964d\u4e3a\u96f6\u3002\u7136\u540e\u5206\u6790\u5f53\u56db\u5206\u4e4b\u4e00\u6ce2\u6216\u534a\u6ce2\u5ef6\u8fdf\u5668\u653e\u7f6e\u5728\u8fd9\u4e24\u4e2a\u504f\u632f\u5668\u4e4b\u95f4\u65f6\u900f\u5c04\u5149\u7ebf\u7684\u5f3a\u5ea6\u548c\u504f\u632f\u3002");

    model.label("linear_wave_retarder.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
