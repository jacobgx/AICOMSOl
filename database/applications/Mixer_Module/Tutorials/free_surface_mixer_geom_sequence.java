/*
 * free_surface_mixer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class free_surface_mixer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("C", "47.2[mm]");
    model.param().descr("C", "\u6405\u62cc\u5668\u95f4\u9699");
    model.param().set("D", "260[mm]");
    model.param().descr("D", "\u6405\u62cc\u5668\u76f4\u5f84");
    model.param().set("Hliq", "700[mm]");
    model.param().descr("Hliq", "\u521d\u59cb\u6db2\u4f53\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "free_surface_mixer.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "1e-3");
    model.component("comp1").geom("geom1").run("sca1");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "D/2");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "2.75*C");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", "D*0.75");
    model.component("comp1").geom("geom1").feature("cone1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("sca1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("cone1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("par1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", "Hliq*2/3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("spl1(1)");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", "0.301[m]");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("par3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("clf1", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf1").set("ignoremerged", true);
    model.component("comp1").geom("geom1").feature("clf1").selection("input").set("fin", 68, 76, 81, 87);
    model.component("comp1").geom("geom1").run("clf1");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("clf1", 130, 131, 137, 138, 160, 161, 162);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("clf2", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf2").set("ignoremerged", true);
    model.component("comp1").geom("geom1").feature("clf2").selection("input").set("ige1", 68, 76, 78, 81);
    model.component("comp1").geom("geom1").run("clf2");
    model.component("comp1").geom("geom1").create("ige2", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige2").selection("input")
         .set("clf2", 140, 141, 142, 143, 147, 148, 149);
    model.component("comp1").geom("geom1").run("ige2");
    model.component("comp1").geom("geom1").create("clf3", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf3").set("ignoremerged", true);
    model.component("comp1").geom("geom1").feature("clf3").selection("input").set("ige2", 80, 84, 87, 90);
    model.component("comp1").geom("geom1").run("clf3");
    model.component("comp1").geom("geom1").create("ige3", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige3").selection("input")
         .set("clf3", 168, 169, 173, 174, 179, 180, 181);
    model.component("comp1").geom("geom1").run("ige3");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("ige3", 4, 5, 76, 77);
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").clear("ige3");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("ige3", 4, 5, 25, 26);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").set("cmf1", 46, 47, 73, 74);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").create("cmf3", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").set("cmf2", 48, 49);
    model.component("comp1").geom("geom1").run("cmf3");
    model.component("comp1").geom("geom1").create("cmf4", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf4").selection("input").set("cmf3", 1, 2, 3, 24);
    model.component("comp1").geom("geom1").run("cmf4");
    model.component("comp1").geom("geom1").create("cmf5", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf5").selection("input")
         .set("cmf4", 5, 8, 10, 17, 18, 19, 29, 31, 33, 38, 39, 40);
    model.component("comp1").geom("geom1").run("cmf5");
    model.component("comp1").geom("geom1").create("ige4", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige4").selection("input")
         .set("cmf5", 98, 101, 102, 103, 112, 113, 119, 120, 121, 122);
    model.component("comp1").geom("geom1").run("ige4");
    model.component("comp1").geom("geom1").create("cmf6", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf6").selection("input").set("ige4", 53, 54);
    model.component("comp1").geom("geom1").run("cmf6");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("cmf6", 6);
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").view("view1").set("transparency", false);

    model.title(null);

    model.description("");

    model.label("free_surface_mixer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
