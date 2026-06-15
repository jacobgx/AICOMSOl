/*
 * tower_sensitivity_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:15 by COMSOL 6.3.0.290. */
public class tower_sensitivity_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Sensitivity_and_Optimization");

    model.param().set("Lx", "1[m]");
    model.param().descr("Lx", "\u57fa\u672c\u5355\u5143\u5bbd\u5ea6");
    model.param().set("Ly", "1[m]");
    model.param().descr("Ly", "\u57fa\u672c\u5355\u5143\u6df1\u5ea6");
    model.param().set("Lz", "1[m]");
    model.param().descr("Lz", "\u57fa\u672c\u5355\u5143\u534a\u9ad8");
    model.param().set("Nz", "5");
    model.param().descr("Nz", "\u57fa\u672c\u5355\u5143\u6570");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx", "Ly", "Lz"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "Ly", "Lz"});
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"Lx", "Ly", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"Lx", "0", "Lz"});
    model.component("comp1").geom("geom1").feature().duplicate("ls4", "ls3");
    model.component("comp1").geom("geom1").feature("ls4").set("coord1", new int[]{0, 0, 0});
    model.component("comp1").geom("geom1").run("ls4");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("blk1", "ls1", "ls2", "ls3", "ls4");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "0", "Lz"});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("ls5", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls5").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls5").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls5").set("coord1", new String[]{"0", "Ly", "Lz"});
    model.component("comp1").geom("geom1").feature("ls5").set("coord2", new String[]{"Lx", "0", "Lz"});
    model.component("comp1").geom("geom1").feature().duplicate("ls6", "ls5");
    model.component("comp1").geom("geom1").feature("ls6").set("coord1", new String[]{"0", "0", "2*Lz"});
    model.component("comp1").geom("geom1").feature("ls6").set("coord2", new String[]{"Lx", "Ly", "2*Lz"});
    model.component("comp1").geom("geom1").run("ls6");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input")
         .set("blk1", "ls1", "ls2", "ls3", "ls4", "ls5", "ls6", "mir1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "1", "Nz"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "2*Lz"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("ccur1").selection("input").set("arr1");
    model.component("comp1").geom("geom1").run("ccur1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5e95\u90e8\u70b9");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "0.001*Lz");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u9876\u90e8\u70b9");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "2*Lz*Nz*0.9999");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u975e\u5782\u76f4\u6746 (x)");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "Ly/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "Ly/2");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u975e\u5782\u76f4\u6746 (y)");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "Lx/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "Lx/2");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u975e\u5782\u76f4\u6746");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel3", "boxsel4"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u6841\u67b6\u5854");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("tower_sensitivity_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
