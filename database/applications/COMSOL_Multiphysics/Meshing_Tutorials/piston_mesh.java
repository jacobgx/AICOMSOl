/*
 * piston_mesh.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class piston_mesh {

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
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "piston_quarter.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();
    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(39);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "0.0002");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.45);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(8, 39);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.8);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();

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
    model.result("pg1").feature("mesh1").set("elemfilter", "quality");
    model.result("pg1").feature("mesh1").set("tetkeep", 0.005);
    model.result("pg1").run();

    model.title("\u8c03\u6574\u975e\u7ed3\u6784\u7f51\u683c\u751f\u6210\u5668\u7684\u5355\u5143\u5927\u5c0f");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u6700\u5c0f\u5355\u5143\u5927\u5c0f\u3001\u66f2\u7387\u5206\u8fa8\u7387\u3001\u72ed\u7a84\u533a\u57df\u5206\u8fa8\u7387\u548c\u6700\u5927\u5355\u5143\u589e\u957f\u7387\u7b49\u7f51\u683c\u53c2\u6570\u3002\u5176\u4e2d\u7684\u64cd\u4f5c\u8bf4\u660e\u8fd8\u8be6\u7ec6\u63cf\u8ff0\u4e86\u5982\u4f55\u8bbf\u95ee\u7f51\u683c\u7edf\u8ba1\u4fe1\u606f\u4ee5\u53ca\u5982\u4f55\u521b\u5efa\u7f51\u683c\u56fe\u3002");

    model.mesh().clearMeshes();

    model.label("piston_mesh.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
