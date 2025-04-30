package aiss.Bitbucket.controller;

import aiss.Bitbucket.model.Commit;
import aiss.Bitbucket.model.User;
import aiss.Bitbucket.service.CommitService;
import aiss.Bitbucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bitbucket")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommitService commitService;

    // LLAMADAS A USER
    @GetMapping("user")
    public User getUser() {
        return userService.getUser();
    }

    /*
    @PostMapping("/bitbucket/users/{username}")
    public User postUser() {
        return userService.getUser();
    }
    */

    // LLAMADAS DE COMMIT
    @GetMapping("{workspace}/{repo_slug}/commit/{commit}")
    public Commit getCommit(
            @PathVariable("workspace") String workspace,
            @PathVariable("repo_slug") String repo_slug,
            @RequestParam( name = "commit", defaultValue = "f5038a72e982b5d35ecb0e83c407c12496697529") String commit) {
        return commitService.getCommit(workspace, repo_slug, commit);
    }

}
