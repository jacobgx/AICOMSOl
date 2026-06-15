/*
 * rectangular_horn_shape_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:44 by COMSOL 6.3.0.290. */
public class rectangular_horn_shape_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "c0/1/h0", "\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("R0", "4*h0", "\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("theta0", "15[deg]", "\u6240\u9700\u7684\u6881\u65cb\u8f6c");
    model.param().set("h0", "0.3[m]", "\u5587\u53ed\u9ad8\u5ea6");
    model.param().set("w0", "h0", "\u5587\u53ed\u5bbd\u5ea6");
    model.param().set("L0", "0.8*h0", "\u5587\u53ed\u957f\u5ea6");
    model.param().set("scaleMax", "0.2", "\u6700\u5927\u4f4d\u79fb");
    model.param().set("Rair", "2*h0", "\u7a7a\u6c14\u534a\u5f84");
    model.param().set("V0", "1[V]", "\u9a71\u52a8\u7535\u538b");
    model.param().set("rSpeaker", "L0/2.2", "\u626c\u58f0\u5668\u534a\u5f84");
    model.param().set("backV", "10[cm^3]", "\u540e\u8154\u4f53");
    model.param().set("meshsz", "c0/f0/6", "\u7f51\u683c\u5927\u5c0f");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "Rair");
    model.component("comp1").geom("geom1").feature("sph1").set("selresult", true);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").named("sph1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("pyr1", "Pyramid");
    model.component("comp1").geom("geom1").feature("pyr1").set("a", "L0");
    model.component("comp1").geom("geom1").feature("pyr1").set("b", "w0");
    model.component("comp1").geom("geom1").feature("pyr1").set("h", "h0");
    model.component("comp1").geom("geom1").feature("pyr1").set("rat", 2);
    model.component("comp1").geom("geom1").feature("pyr1").set("pos", new String[]{"0", "0", "-h0"});
    model.component("comp1").geom("geom1").feature("pyr1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pyr1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7403\u548c\u91d1\u5b57\u5854");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sph1", "pyr1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "-h0");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "rSpeaker");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u51cf\u53bb\u7684\u6846\u9009");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*Rair", "Rair", "Rair+h0"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-Rair", "-Rair", "-h0"});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("blk1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").label("\u5916\u573a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("ballsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("ballsel1").set("posy", "Rair*0.02");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posz", "Rair");
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", "Rair*0.01");
    model.component("comp1").geom("geom1").run("ballsel1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u626c\u58f0\u5668\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "-h0*0.9");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u626c\u58f0\u5668\u9a71\u52a8\u5668");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "rSpeaker*1.01");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u79fb\u52a8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "-h0*0.9");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "-h0*0.8");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "eps");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");

    model.title(null);

    model.description("");

    model.label("rectangular_horn_shape_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
