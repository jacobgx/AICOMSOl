/*
 * exhaust_manifold_create_fluid_domain.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:41 by COMSOL 6.3.0.290. */
public class exhaust_manifold_create_fluid_domain {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Inventor\\Tutorials,_CAD_Import");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "exhaust_manifold.x_b");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").defeaturing("ReplaceFaces").selection("input")
         .set("imp1(1)", 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 88, 89, 90, 91, 94, 95);
    model.component("comp1").geom("geom1").defeaturing("ReplaceFaces").delete("rfa1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").add("rfa1", 32, 33);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("imp1(3)", 42, 43, 44, 65, 66, 87, 88, 100, 220, 221, 222, 252, 253, 298, 299, 345, 600, 601, 602, 636, 637, 673, 674, 684, 770, 771, 772, 789, 790, 807, 808, 818);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("imp1(2)", "imp1(3)", "rfa1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").measure().selection().set("uni1");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").defeaturing("DetectInterferences").find();
    model.component("comp1").geom("geom1").defeaturing("DetectInterferences").localState(true);
    model.component("comp1").geom("geom1").defeaturing("DetectInterferences").localState(false);

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init();
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1(3)");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("imp1(2)", 4);
    model.component("comp1").geom("geom1").feature("wp1").label("\u57ab\u7247\u5750\u6807\u7cfb");
    model.component("comp1").geom("geom1").run("wp1");

    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("imp1(3)", 83);
    model.component("comp1").geom("geom1").feature("wp2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u6b67\u7ba1\u5750\u6807\u7cfb");

    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").set("hidestatus", "hide");
    model.component("comp1").view("view1").hideObjects().clear();

    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("rt1", "RigidTransform");
    model.component("comp1").geom("geom1").feature("rt1").selection("input").set("imp1(2)");
    model.component("comp1").geom("geom1").feature("rt1").set("workplaneobj", "wp1");
    model.component("comp1").geom("geom1").feature("rt1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("rt1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cap1", "CapFaces");
    model.component("comp1").geom("geom1").feature("cap1").selection("input").named("sel1");
    model.component("comp1").geom("geom1").run("cap1");
    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").defeaturing("Fillets").set("entsize", 0.003);
    model.component("comp1").geom("geom1").defeaturing("Fillets").find();
    model.component("comp1").geom("geom1").defeaturing("Fillets").deleteAll("dfi1");
    model.component("comp1").geom("geom1").defeaturing("Holes").set("entsize", "10[mm]");
    model.component("comp1").geom("geom1").defeaturing("Holes").find();
    model.component("comp1").geom("geom1").defeaturing("Holes").deleteAll("dho1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", true);

    model.component("comp1").mesh("mesh1").run();

    model.title("\u5728\u56fa\u4f53\u7ed3\u6784\u4e2d\u521b\u5efa\u6d41\u4f53\u57df");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u5bfc\u5165\u7684 CAD \u88c5\u914d\u4e2d\u4e3a\u6d41\u4f53\u521b\u5efa\u57df\u3002\u6b64\u5916\uff0c\u60a8\u8fd8\u5c06\u5b66\u4e60\u5982\u4f55\u68c0\u6d4b\u548c\u89e3\u6790\u5bf9\u8c61\u4e4b\u95f4\u7684\u95f4\u9699\u548c\u91cd\u53e0\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u7279\u5f81\u53bb\u9664\u5de5\u5177\u5220\u9664\u5706\u89d2\u548c\u901a\u5b54\u3002");

    model.mesh().clearMeshes();

    model.label("exhaust_manifold_create_fluid_domain.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
