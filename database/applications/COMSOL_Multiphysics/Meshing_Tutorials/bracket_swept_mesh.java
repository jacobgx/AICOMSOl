/*
 * bracket_swept_mesh.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class bracket_swept_mesh {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");

    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("imp1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface").set("imp1", 8, 38);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("rendermesh", false);

    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(12, 20, 42);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "6[mm]");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();

    model.component("comp1").view("view1").set("rendermesh", true);

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7f51\u683c\u56fe 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("filteractive", true);
    model.result("pg1").feature("mesh1").set("logfilterexpr", "qualskewness<0.3");
    model.result("pg1").feature("mesh1").set("elemscale", 0.9);
    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("imp1", 7);
    model.component("comp1").geom("geom1").feature("wp1").set("offset", "0.02[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.033[m]", "0.024[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-0.06[m]", "0.086[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0.087[m]", "0.196[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0.087[m]", "-0.196[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.14[m]", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").runPre("pard1");
    model.component("comp1").geom("geom1").feature("pard1").selection("object").named("ext1");
    model.component("comp1").geom("geom1").feature("pard1").set("keepobject", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("ext1");
    model.component("comp1").geom("geom1").cleanup().set("detailsize", 3.4E-4);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 4, 5, 6, 9);
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "quad");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(11, 36);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(42, 43, 47, 48, 78, 79, 83, 84);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("elemtype3", "pyr");
    model.result("pg1").feature("mesh1").set("filteractive", false);
    model.result("pg1").feature("mesh1").set("elemcolor", "magenta");
    model.result("pg1").feature("mesh1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").feature("filt1").set("expr", "(x<-0.08)*(z>0.02)*(y>-0.13)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("mesh2", "mesh1");
    model.result("pg1").run();
    model.result("pg1").feature("mesh2").set("elemtype3", "hex");
    model.result("pg1").feature("mesh2").set("elemcolor", "gray");
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("mesh2").set("titletype", "none");
    model.result("pg1").run();

    model.title("\u652f\u67b6\u51e0\u4f55\u7684\u626b\u63a0\u7f51\u683c");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5206\u5272\u4e09\u7ef4\u96f6\u4ef6\u4ee5\u521b\u5efa\u626b\u63a0\u7f51\u683c\uff0c\u5176\u4e2d\u63a2\u8ba8\u4e86\u4e0d\u540c\u7684\u51e0\u4f55\u5206\u5272\u7b56\u7565\uff0c\u5e76\u6f14\u793a\u5982\u4f55\u5c06\u626b\u63a0\u7f51\u683c\u4e0e\u81ea\u7531\u7f51\u683c\u8fdb\u884c\u7ec4\u5408\u3002");

    model.mesh().clearMeshes();

    model.label("bracket_swept_mesh.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
