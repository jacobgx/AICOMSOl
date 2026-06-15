/*
 * fda_benchmark_blood_pump_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class fda_benchmark_blood_pump_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Sf", "1/10", "\u6bd4\u4f8b\u56e0\u5b50");
    model.param().set("z_os_rd", "0.0035", "\u65cb\u8f6c\u57df\u7684\u5782\u76f4\u504f\u79fb");
    model.param().set("r_rd", "0.056/2", "\u65cb\u8f6c\u57df\u7684\u534a\u5f84");
    model.param()
         .set("r_rd_h", "0.010/2", "\u65cb\u8f6c\u57df\u7684\u534a\u5f84\uff0c\u8f74\u5fc3\u56f4\u62a4\u7ed3\u6784");
    model.param().set("z_wp5", "0.16", "\u5de5\u4f5c\u5e73\u9762 5 \u7684\u5782\u76f4\u504f\u79fb");
    model.param().set("x_wp6", "0.02", "\u5de5\u4f5c\u5e73\u9762 6 \u7684\u6cd5\u5411\u504f\u79fb");
    model.param().set("x_in", "0.1", "\u5165\u53e3\u7ba1\u957f\u5ea6 x \u65b9\u5411");
    model.param().set("r_h", "0.004", "\u8f74\u5fc3\u534a\u5f84");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "fda_benchmark_blood_pump_rotor_cad_geometry.stp");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "Sf");
    model.component("comp1").geom("geom1").run("sca1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("sca1", 16);
    model.component("comp1").geom("geom1").feature("wp1").set("offset", "z_os_rd");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_rd");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext1").selection("vertex").set("sca1", 81);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext1", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "r_rd_h");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext2").selection("vertex").set("sca1", 68);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "edgeparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("planaredge").set("sca1", 224);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "r_rd_h");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("pos", new String[]{"0", "r_h"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2", "rev1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("imp2").set("type", "cad");
    model.component("comp1").geom("geom1").feature("imp2").set("repair", false);
    model.component("comp1").geom("geom1").feature("imp2").set("importtol", 1.0E-8);
    model.component("comp1").geom("geom1").feature("imp2")
         .set("filename", "fda_benchmark_blood_pump_housing_cad_geometry.stp");
    model.component("comp1").geom("geom1").feature("imp2").importData();
    model.component("comp1").geom("geom1").defeaturing("Fillets").selection("input").init();
    model.component("comp1").geom("geom1").defeaturing("Fillets").selection("input").set("imp2");
    model.component("comp1").geom("geom1").defeaturing("Fillets").find();
    model.component("comp1").geom("geom1").defeaturing("Fillets").set("entsize", 0.1);
    model.component("comp1").geom("geom1").defeaturing("Fillets").find();
    model.component("comp1").geom("geom1").defeaturing("Fillets").detail().setGroup(1);
    model.component("comp1").geom("geom1").defeaturing("Fillets").delete("dfi1");
    model.component("comp1").geom("geom1").create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("sca2").selection("input").set("dfi1");
    model.component("comp1").geom("geom1").feature("sca2").set("isotropic", "Sf");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("fil1", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.00004");
    model.component("comp1").geom("geom1").feature("fil1").selection("edge").set("sca2", 17, 19);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("fil1", "uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("sca1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "edgeparallel");
    model.component("comp1").geom("geom1").feature("wp4").selection("planaredge").set("dif1", 129);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("dif1", 1, 2);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "edgeparallel");
    model.component("comp1").geom("geom1").feature("wp5").selection("planaredge").set("pard1", 129);
    model.component("comp1").geom("geom1").feature("wp5").set("offset", "z_wp5");
    model.component("comp1").geom("geom1").feature("wp5").set("reverse", true);
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("pard1", 1, 2);
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("planetype", "edgeparallel");
    model.component("comp1").geom("geom1").feature("wp6").selection("planaredge").set("pard2", 485);
    model.component("comp1").geom("geom1").feature("wp6").set("offset", "x_wp6");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").create("pard3", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard3").selection("domain").set("pard2", 1, 2);
    model.component("comp1").geom("geom1").run("pard3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("pard3", 213);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "x_in", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input")
         .set("fin", 18, 20, 22, 23, 26, 27, 28, 29, 43, 48, 50, 51, 54, 55, 58, 59);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").set("cmf1", 40, 41, 42, 43, 48, 49);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").create("cmf3", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input")
         .set("cmf2", 68, 72, 78, 95, 96, 97, 102, 104, 121, 129, 144, 147);
    model.component("comp1").geom("geom1").run("cmf3");
    model.component("comp1").geom("geom1").create("cmf4", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf4").selection("input").set("cmf3", 80, 87, 90, 119, 123, 134);
    model.component("comp1").geom("geom1").run("cmf4");
    model.component("comp1").geom("geom1").create("cmf5", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf5").selection("input")
         .set("cmf4", 143, 146, 148, 149, 151, 155, 156, 159, 160, 162, 164, 165, 166, 167, 168, 169);
    model.component("comp1").geom("geom1").run("cmf5");
    model.component("comp1").geom("geom1").create("cmf6", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf6").selection("input")
         .set("cmf5", 149, 151, 152, 153, 154, 155);
    model.component("comp1").geom("geom1").run("cmf6");
    model.component("comp1").geom("geom1").create("cmf7", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf7").selection("input")
         .set("cmf6", 70, 72, 82, 83, 89, 94, 95, 97, 98, 99, 117, 118, 122, 126, 131, 137, 138, 139);
    model.component("comp1").geom("geom1").run("cmf7");
    model.component("comp1").geom("geom1").create("cmf8", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf8").selection("input").set("cmf7", 83, 86, 88, 114, 116, 123);
    model.component("comp1").geom("geom1").run("cmf8");
    model.component("comp1").geom("geom1").create("cmf9", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf9").selection("input")
         .set("cmf8", 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 32, 39, 40, 41, 42, 43, 44, 45, 46, 47, 60, 65, 66, 67, 68, 69, 70, 71, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 148, 149, 153, 154, 155, 156, 157, 158, 159, 160, 161);
    model.component("comp1").geom("geom1").run("cmf9");
    model.component("comp1").geom("geom1").create("cmf10", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf10").selection("input").set("cmf9", 13, 14, 15, 16, 48);
    model.component("comp1").geom("geom1").run("cmf10");
    model.component("comp1").geom("geom1").create("cmf11", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf11").selection("input").set("cmf10", 40, 41, 44);

    model.title(null);

    model.description("");

    model.label("fda_benchmark_blood_pump_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
