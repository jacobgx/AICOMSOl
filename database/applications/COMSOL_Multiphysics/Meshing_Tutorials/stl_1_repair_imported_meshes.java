/*
 * stl_1_repair_imported_meshes.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class stl_1_repair_imported_meshes {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");
    model.mesh("mpart1").create("imp1", "Import");

    model.component("mcomp1").baseSystem(null);

    model.geom("mgeom1").lengthUnit("mm");

    model.mesh("mpart1").feature("imp1").set("filename", "c3_vertebra.stl");
    model.mesh("mpart1").feature("imp1").set("createdom", true);
    model.mesh("mpart1").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").feature("imp1").setIndex("outsel_bnd", "C3", 0);
    model.mesh("mpart1").create("fill1", "FillHoles");
    model.mesh("mpart1").feature("fill1").set("createdom", true);
    model.mesh("mpart1").feature("fill1").selection().named("imp1_mesh");
    model.mesh("mpart1").run("fill1");

    model.component().create("mcomp2", "MeshComponent");

    model.geom().create("mgeom2", 3);

    model.mesh().create("mpart2", "mgeom2");
    model.mesh("mpart2").create("imp1", "Import");

    model.component("mcomp2").baseSystem(null);

    model.geom("mgeom2").lengthUnit("mm");

    model.mesh("mpart2").feature("imp1").set("filename", "intdiscC_3-4.stl");
    model.mesh("mpart2").feature("imp1").set("createdom", true);
    model.mesh("mpart2").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart2").feature("imp1").importData();
    model.mesh("mpart2").feature("imp1").setIndex("outsel_bnd", "C3-C4 disc", 0);
    model.mesh("mpart2").create("edg1", "CreateEdges");
    model.mesh("mpart2").feature("edg1").set("edgespec", "meshedge");
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.550540924072266, 0, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 1.2008996009826651, 0, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.79849243164065, 0, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.394841194152836, 1, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.9365212023258205, 1, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.73487186431885, 1, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.467785835266113, 2, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.4278857856988905, 2, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.74988746643065, 2, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.768284320831304, 3, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -0.11785925924778001, 3, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.8425588607788, 3, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.980187892913825, 4, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.064084231853485, 4, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.91245555877685, 4, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.835389137268065, 5, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.8742076754570001, 5, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.8834047317505, 5, 2);
    model.mesh("mpart2").feature("edg1").set("selfac", true);
    model.mesh("mpart2").run("edg1");
    model.mesh("mpart2").create("dele1", "DeleteEntities");
    model.mesh("mpart2").feature("dele1").selection().named("edg1_selfac");
    model.mesh("mpart2").run("dele1");
    model.mesh("mpart2").create("fill1", "FillHoles");
    model.mesh("mpart2").feature("fill1").set("createdom", true);
    model.mesh("mpart2").feature("fill1").selection().named("imp1_mesh");
    model.mesh("mpart2").run("fill1");

    model.component().create("mcomp3", "MeshComponent");

    model.geom().create("mgeom3", 3);

    model.mesh().create("mpart3", "mgeom3");
    model.mesh("mpart3").create("imp1", "Import");

    model.component("mcomp3").baseSystem(null);

    model.geom("mgeom3").lengthUnit("mm");

    model.mesh("mpart3").feature("imp1").set("filename", "c4_vertebra.stl");
    model.mesh("mpart3").feature("imp1").set("createdom", true);
    model.mesh("mpart3").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart3").feature("imp1").importData();
    model.mesh("mpart3").feature("imp1").set("stltoltype", "absolute");
    model.mesh("mpart3").feature("imp1").set("stltolabs", "5e-4[mm]");
    model.mesh("mpart3").feature("imp1").importData();
    model.mesh("mpart3").feature("imp1").setIndex("outsel_dom", "C4", 0);
    model.mesh("mpart3").feature("imp1").setIndex("outsel_bnd", "C4", 0);

    model.component().create("mcomp4", "MeshComponent");

    model.geom().create("mgeom4", 3);

    model.mesh().create("mpart4", "mgeom4");
    model.mesh("mpart4").create("imp1", "Import");

    model.component("mcomp4").baseSystem(null);

    model.geom("mgeom4").lengthUnit("mm");

    model.mesh("mpart4").feature("imp1").set("source", "sequence");
    model.mesh("mpart4").feature("imp1").set("buildsource", true);
    model.mesh("mpart4").feature("imp1").set("sequence", "mpart1");
    model.mesh("mpart4").feature("imp1").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp1").importData();
    model.mesh("mpart4").create("imp2", "Import");
    model.mesh("mpart4").feature("imp2").set("source", "sequence");
    model.mesh("mpart4").feature("imp2").set("buildsource", true);
    model.mesh("mpart4").feature("imp2").set("sequence", "mpart2");
    model.mesh("mpart4").feature("imp2").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp2").importData();
    model.mesh("mpart4").create("imp3", "Import");
    model.mesh("mpart4").feature("imp3").set("source", "sequence");
    model.mesh("mpart4").feature("imp3").set("buildsource", true);
    model.mesh("mpart4").feature("imp3").set("sequence", "mpart3");
    model.mesh("mpart4").feature("imp3").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp3").importData();
    model.mesh("mpart4").feature("imp3").create("tr1", "Transform");
    model.mesh("mpart4").feature("imp3").feature("tr1").set("displ", new String[]{"0", "0", "1[mm]"});
    model.mesh("mpart4").run("imp3");

    model.view("view5").set("clippingactive", true);
    model.view("view5").clip().create("plane1", "ClipPlane");
    model.view("view5").clip("plane1").set("orientationaxes", new double[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}});
    model.view("view5").clip("plane1").set("position", new double[]{2.7920684814453516, 0, -30.637358188629154});
    model.view("view5").clip("plane1").set("translationamount", 4.304107463836671);
    model.view("view5").clip("plane1")
         .set("orientationaxes", new String[][]{{"0", "1", "0"}, {"0", "0", "1"}, {"1", "0", "0"}});
    model.view("view5").clip("plane1").set("invertclipping", true);
    model.view("view5").set("clipshowcappedfaces", true);
    model.view("view5").set("clipcappedfacescolorize", false);

    model.mesh("mpart4").create("uni1", "Union");
    model.mesh("mpart4").feature("uni1").set("toltype", "absolute");
    model.mesh("mpart4").feature("uni1").set("abstol", "6e-4[mm]");
    model.mesh("mpart4").feature("uni1").set("placement", "linear");
    model.mesh("mpart4").run("uni1");
    model.mesh("mpart4").create("join1", "JoinEntities");
    model.mesh("mpart4").feature("join1").selection().set(1, 3, 4, 5);
    model.mesh("mpart4").run("join1");

    model.view("view5").set("renderwireframe", true);
    model.view("view5").set("rendermesh", false);
    model.view("view5").set("clippingactive", false);

    model.mesh("mpart4").create("mrg1", "MergeEntities");
    model.mesh("mpart4").feature("mrg1").selection().set(6, 7, 8, 9, 10, 11);
    model.mesh("mpart4").run("mrg1");
    model.mesh("mpart4").create("join2", "JoinEntities");
    model.mesh("mpart4").feature("join2").selection().geom("mgeom4", 2);
    model.mesh("mpart4").feature("join2").selection().set(3, 6, 7, 8);
    model.mesh("mpart4").run("join2");

    model.view("view5").set("rendermesh", true);
    model.view("view5").set("showgrid", false);
    model.view("view5").set("showaxisorientation", false);
    model.view("view5").set("showgrid", true);
    model.view("view5").set("showaxisorientation", true);

    model.mesh("mpart4").create("pln1", "IntersectPlane");
    model.mesh("mpart4").feature("pln1").set("quickplane", "zx");
    model.mesh("mpart4").feature("pln1").set("selabove", true);
    model.mesh("mpart4").feature("pln1").set("selaboveshow", "bnd");
    model.mesh("mpart4").feature("pln1").set("selbelow", true);
    model.mesh("mpart4").feature("pln1").set("selbelowshow", false);
    model.mesh("mpart4").run("pln1");
    model.mesh("mpart4").create("dele1", "DeleteEntities");
    model.mesh("mpart4").feature("dele1").selection().named("pln1_dombelow");
    model.mesh("mpart4").run("dele1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("source", "sequence");
    model.component("comp1").mesh("mesh1").feature("imp1").set("buildsource", true);
    model.component("comp1").mesh("mesh1").feature("imp1").set("sequence", "mpart4");
    model.component("comp1").mesh("mesh1").feature("imp1").set("unmesheddom", true);
    model.component("comp1").mesh("mesh1").feature("imp1").importData();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh2").autoMeshSize(7);
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").stat().selection().geom(3);
    model.component("comp1").mesh("mesh2").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh2").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh2").stat().selection().allGeom();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.title("STL \u5bfc\u5165 1 - \u4fee\u590d\u548c\u7ec4\u5408 STL \u6587\u4ef6");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5904\u7406\u5bfc\u5165\u7684\u8868\u9762\u7f51\u683c\u3002\u5176\u4e2d\u7684\u64cd\u4f5c\u8bf4\u660e\u8be6\u7ec6\u4ecb\u7ecd\u4e86\u5982\u4f55\u5bfc\u5165\u4e24\u4e2a\u690e\u9aa8\u548c\u4e00\u4e2a\u690e\u95f4\u76d8\u7684 STL \u6587\u4ef6\uff0c\u4ee5\u53ca\u5982\u4f55\u4fee\u590d\u5b54\u3001\u9519\u4f4d\u7684\u7f51\u683c\u9876\u70b9\u548c\u76f8\u4ea4\u5355\u5143\u3002\u6b64\u5916\uff0c\u672c\u6559\u7a0b\u8fd8\u8fdb\u4e00\u6b65\u6f14\u793a\u4e86\u5982\u4f55\u5c06\u8fd9\u4e9b\u7f51\u683c\u8fdb\u884c\u7ec4\u5408\u5e76\u4f7f\u5176\u76f8\u4ea4\uff0c\u91cd\u65b0\u5212\u5206\u8868\u9762\u7f51\u683c\uff0c\u4ee5\u53ca\u5982\u4f55\u751f\u6210\u56db\u9762\u4f53\u7f51\u683c\u4ee5\u4fbf\u5728\u751f\u6210\u7684\u57df\u4e2d\u8fdb\u884c\u4eff\u771f\u3002");

    model.label("stl_1_repair_imported_meshes.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
